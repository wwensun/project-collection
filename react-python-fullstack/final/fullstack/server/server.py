from flask import Flask, render_template,send_file
from flask_restful import Resource, Api, reqparse, request

import os
import numpy as np # linear algebra
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
import matplotlib
#matplotlib.use('TkAgg')
import seaborn as sns

import json
from collections import namedtuple


app = Flask(__name__, static_folder="../static/dist", template_folder="../static")
api = Api(app)
parser = reqparse.RequestParser()


parser.add_argument('data')


path = '/Users/sunwen/Desktop/final/fullstack'
file_path ='./data/BlackFriday.csv'
df = pd.read_csv(file_path)

def check_image_exist(original_file_path):
    if os.path.isfile(original_file_path):
        os.remove(original_file_path)

#data preprocessing
def data_preprocessing():
    gender_plot = sns.countplot(df['Gender'])
    gender_plot = gender_plot.get_figure()
    check_image_exist("../static/dist/images/gender.png")
    gender_plot.savefig("../static/dist/images/gender.png")
    gender_plot.clf()

    age_plot = sns.countplot(df['Age'],hue=df['Gender'])
    age_plot = age_plot.get_figure()
    check_image_exist("../static/dist/images/age-gender.png")
    age_plot.savefig("../static/dist/images/age-gender.png")
    age_plot.clf()

    agee_plot = sns.countplot(df['Age'])
    agee_plot = agee_plot.get_figure()
    check_image_exist("../static/dist/images/age.png")
    agee_plot.savefig("../static/dist/images/age.png")
    agee_plot.clf()

    occu_plot = sns.countplot(df['Occupation'])
    occu_plot = occu_plot.get_figure()
    check_image_exist("../static/dist/images/occupation.png")
    occu_plot.savefig("../static/dist/images/occupation.png")
    occu_plot.clf()


class analysis(Resource):
    def get(self):
        data_preprocessing()
        image_path=['gender.png','age-gender.png','age.png','occupation.png']
        return image_path
api.add_resource(analysis,'/general')

def parse_age(age):
    if int(age)<=17:
        age="0-17"
    elif int(age)<=25:
        age="18-25"
    elif int(age)<=35:
        age="26-35"
    elif int(age)<=45:
        age="36-45"
    elif int(age)<=55:
        age = "46-55"
    else:
        age= "55+"
    return age

def parse_gender(gender):
    if gender=="Female":
        gen = "F"
    else:
        gen = "M"
    return gen
def parse_mar(marital):
    if marital=="Married":
        mar = 1
    else:
        mar = 0
    return mar

def data_process(data):
    gen = parse_gender(data['gender'])
    mar = parse_mar(data['maritual'])
    age = parse_age(data['age'])
    ocu = 0
    pur = int(data['purchase'])
    year = 0
    d = {'Gender':[gen],'Age':[age],'Occupation':[ocu],'Stay_In_Current_City_Years':[year],'Marital_Status':[mar],'Purchase':[pur]}
    df2 = pd.DataFrame(data=d)
    return df2  

def predict(data):
    gen = parse_gender(data['gender'])
    mar = parse_mar(data['maritual'])
    age = parse_age(data['age'])

    goal = df[df["Age"]==age]
    goal = goal[goal["Gender"]==gen]
    goal = goal[goal["Marital_Status"]==mar]

    return goal['Purchase'].mean()


class input_data(Resource):
    def post(self):
        user_data = parser.parse_args()
        data = user_data.data
        data = data.replace("'", '"')
        data = json.loads(data)
        
        df2 = data_process(data)
        df = pd.read_csv(file_path)
        df= df.append(df2,ignore_index=True)
        print(df.count())
        return df2.to_json()
api.add_resource(input_data,'/input')

class predict_purchase(Resource):
    def post(self):
        user_data = parser.parse_args()
        data = user_data.data
        data = data.replace("'", '"')
        data = json.loads(data)
        pre = predict(data)
        print(pre)
        return pre
api.add_resource(predict_purchase,'/predict')

@app.route("/")
def index():
    return render_template("index.html")


if __name__ == "__main__":
    app.run(debug=True)