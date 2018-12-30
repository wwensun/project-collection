import React from 'react';

const Predict = ({excutePredict}) => {
    function wrapData(){
        const gender = document.getElementById("gender").value;
        const maritual = (document.getElementById("maritual").value);
        const age = document.getElementById("new-age").value;

        const data = {"gender":gender,"maritual":maritual,"age":age};


        excutePredict(data)
    }
    return(
        <div className="predict-content">
            <ul>
                <li>
                    <select id="gender">
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                </li>
                <li>
                <select id = "maritual">
                    <option value="Married">Married</option>
                    <option value="Unmarried">Unmarried</option>
                </select>
                </li>
                <li><input placeholder="input age here" id = "new-age"/></li>
                <li><button id = "predict" onClick={wrapData}>Predict</button></li>
            </ul>
        </div>
    )
    
};

export default Predict;