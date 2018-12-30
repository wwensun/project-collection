import React from 'react';
import {getGeneralAnalysis,inputNewData,predictPurchase} from './services';

import Display from './component/Display';
import Input from './component/Input';
import Predict from './component/Predict';

//import gender from '../images/public/gender.png';

require('../css/style.css');


class App extends React.Component {
  constructor(){
    super();
    this.state ={
      choice:{display:0,
              input:0,
              predict:0}
    };
    this.getAnalysis = this.getAnalysis.bind(this);
    this.getInput = this.getInput.bind(this);
    this.getPredict = this.getPredict.bind(this);
    this.displayImage = this.displayImage.bind(this);
    this.clearDisplayPanel = this.clearDisplayPanel.bind(this);
    this.InputData = this.InputData.bind(this);
    this.userPredict = this.userPredict.bind(this);
    this.displayPredict = this.displayPredict.bind(this);
    this.displayInputResult = this.displayInputResult.bind(this);
  };

  displayImage(imagesPath){
    
    const panel = document.querySelector(".display-content");
    console.log(imagesPath);
    let html='';
    for(let i=0,len=imagesPath.length;i<len;i++){
      html = html+`<img src='dist/images/${imagesPath[i]}' alt='${imagesPath[i]}' />`
    }
    console.log(html);
    panel.innerHTML = html;
  };

  getAnalysis(){
    this.clearDisplayPanel();
    this.setState({
      choice:{display:1,
              input:0,
              predict:0}
    })
    getGeneralAnalysis()
    .then(path=>{this.displayImage(path)})
    .catch(error => {
      console.warn(`ERROR: ${error}`);
    });
    
    
  };

  getInput(){

    this.clearDisplayPanel();
    this.setState({
      choice:{display:0,
              input:1,
              predict:0}
    })
  };

  getPredict(){
    this.clearDisplayPanel();
    this.setState({
      choice:{display:0,
              input:0,
              predict:1}
    })
  };

  clearDisplayPanel(){
    const display  = document.querySelector(".display-content");
    const input  = document.querySelector(".input-content");
    const predict  = document.querySelector(".predict-content");
    const result = document.querySelector(".result-panel");
    
    if(display!==null){
      display.innerHTML = "";
    }
    if(input!==null){
      input.innerHTML = "";
    }
    if(predict!==null){
      predict.innerHTML = "";
    }
    result.innerHTML = "";


  }

  InputData(data){
    inputNewData(data)
    .then(this.displayInputResult)
    .catch(error => {
      console.warn(`ERROR: ${error}`);
    });
  }
  userPredict(data){
    predictPurchase(data)
    .then(data=>{this.displayPredict(Math.round(data))})
    .catch(error => {
      console.warn(`ERROR: ${error}`);
    });
  }

  displayPredict(data){
    console.log(data)
    const predict  = document.querySelector(".result-panel");
    predict.innerHTML = `<div>According to our data, you will spend ${data} dollars during Black Friday</div>`;

  }
  displayInputResult(){
    const result  = document.querySelector(".result-panel");
    result.innerHTML = `<div>Successfully inserted</div>`;
  }

  


  render(){ 
    const isDisPlay = (this.state.choice.predict===0 && this.state.choice.input===0 && this.state.choice.display===1);
    const isInput = (this.state.choice.display===0 && this.state.choice.predict===0 && this.state.choice.input===1);
    const isPredict = (this.state.choice.display===0 && this.state.choice.input===0 && this.state.choice.predict===1);
    return (
      <div className="App">
        <div className="choice-panel">
        <ul>
          <li><button onClick={this.getAnalysis}>get data</button></li>
          <li><button onClick={this.getInput}>input data</button></li>
          <li><button onClick={this.getPredict}>predict</button></li>
        </ul>   
          
        </div>
        
        <div className="content-wrap">
          <h1>Come and See How Much You Will Spend</h1>
          <div className="display-panel">
            {isDisPlay && <Display />}
            {isInput && <Input excuteInput={this.InputData}/>}
            {isPredict && <Predict excutePredict={this.userPredict}/>}
          </div>
          <div className="result-panel">
              <img src="dist/images/shopping.jpg" />
          </div>
        </div>
      </div>

      
    );
  }
}

export default App;