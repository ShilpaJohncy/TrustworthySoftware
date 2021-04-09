import React from "react";

class Documentation extends React.Component {
    render() {
        return (
            <div className={"contents"}>
                <div className={"content-box"}>
                    <div className="textbox">
                        <h1>About Trustworthy Analyser</h1>
                        <p> Trustworthy analyser is a tool intended to test the trustworthiness of an application based on 5 facets: </p>
                        <ul>
                            <li style={{'text-align': 'left'}}> Security - Ability of a software to remain protected against malicious hazards</li>
                            <li style={{'text-align': 'left'}} > Safety - Ability of the software to operate without causing harm to anyone or anything</li>
                            <li style={{'text-align': 'left'}} > Resiliency - Ability of the software to recover from errors quickly and completely</li>
                            <li style={{'text-align': 'left'}} > Reliability - Ability of the software to operate 'correctly' or as intended</li>
                            <li style={{'text-align': 'left'}} > Availability - Ability of the software to operate when required</li>
                        </ul>
                        <p> Each of the above facets are scored based on the weighted results from testing multiple features.</p>
                        <p> Find out more about the trustworthy analyser by exploring its codebase on <a href={"https://github.com/ShilpaJohncy/TrustworthySoftware"}>GitHub</a></p>
                        <p> And if you have more questions or ideas to improve this tool, feel free to reach out at shilpamaria.johncy@gmail.com </p>
                        <p>Keep in touch...</p>
                    </div>
                </div>
            </div>

         );
    }
}

export default Documentation