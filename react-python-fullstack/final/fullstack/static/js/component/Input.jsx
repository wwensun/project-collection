import React from 'react';

const Input = ({
    excuteInput
}) => {
    function wrapData(){
        const gender = document.getElementById("gender").value;
        const maritual = (document.getElementById("maritual").value);
        const age = document.getElementById("new-age").value;
        const occupation = document.getElementById("new-occupation").value;
        const purchase = document.getElementById("new-purchase").value;
        const data = {"gender":gender,"maritual":maritual,"age":age,"occupation":occupation,"purchase":purchase};


        excuteInput(data)
    }
    return(
        <div className="input-content">
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
            <li><input placeholder="input occupation here" id = "new-occupation"/></li>
            <li><input placeholder="input purchase amount here" id = "new-purchase"/></li>
            <li><button id = "new-data" onClick={wrapData}>Submit</button></li>
        </ul>
        </div>
    )
    
};

export default Input;
