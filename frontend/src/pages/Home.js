import React, {Component} from "react";
import {faQuestionCircle, faAsterisk} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Button, Popup} from "semantic-ui-react"
import Result from "./Result";

let total = 0;

function validateInput(exePath){
    let regex = /([\w](\:))(([\w|\s]*\\)*)+([\w]*)+(.EXE|.exe)/
    if(!regex.test(exePath)){
        alert("Uh oh! Looks like you chose a wrong file.")
        return false
    }
    return true
}

function findTotal() {
    let tot = 0;
    let arr = document.getElementsByName('percentage');
    for (let i = 0; i < arr.length; i++) {
        if (parseInt(arr[i].value)) {
            tot += parseInt(arr[i].value);
            if (tot > 100) {
                alert("Please make sure numbers total 100.");
                arr[i].value = null;
                return false;
            }
            total = tot;
        }
    }
}

function ifSmallerSum() {
    let arr = document.getElementsByName('percentage');
    if (total < 100 && total > 0) {
        if(arr[arr.length - 1].value > 0){
            arr[arr.length - 1].value = null;
        }
        alert("Please make sure weightages total 100");
        return false;
    }
    return true;
}

class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            redirect: false
        };
        this.onInputChange = this.onInputChange.bind(this);
        this.onSubmitForm = this.onSubmitForm.bind(this);
        this.setPercentage = this.setPercentage.bind(this);
    }

    async onInputChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        });
    }
    
    setPercentage(event){
        this.setState({
            [event.target.id]:event.target.value
        });
    }

    requiredFields(){
        const isEnabled = this.state.vendor && this.state.product && this.state.exe;
        return isEnabled;
    }

    onSubmitForm(event) {
        if (ifSmallerSum() && validateInput(this.state.exe)) {
            this.setState({ redirect: true });
            event.preventDefault();

        }
    }

    render() {
        if (this.state.redirect) {
            return <Result message={JSON.stringify(this.state)} />
        }

        return (
            <div className="contents">
                <h1>Trustworthy Analyser</h1>
                <div className="wrapper">
                    <div className="box">
                        <div>
                            <form>
                                <Popup content='The path to the location of the application.exe to be tested'
                                       trigger={<label className={"field-label input-group-addon"}> Application Path
                                           <FontAwesomeIcon icon={faQuestionCircle} className={"qmark"}/>
                                           <FontAwesomeIcon icon={faAsterisk} className={"asterix"}/>
                                       </label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>

                                <input name={"exe"} className={"file-browse input-group-addon"} type="text"
                                       value={this.state.exe}
                                       pattern="/([\w](\:))(([\w|\s]*\\)*)+([\w]*)+(.EXE|.exe)/"
                                       placeholder={"C:\\fakepath\\application.exe"}
                                       onChange={this.onInputChange}/>
                            </form>

                            <form>
                                {/* Vendor field */}
                                <Popup content='Name of the company selling the software'
                                       trigger={<label className={"field-label input-group-addon"}> Vendor
                                           <FontAwesomeIcon icon={faQuestionCircle} className={"qmark"}/>
                                           <FontAwesomeIcon icon={faAsterisk} className={"asterix"}/>
                                       </label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input name={"vendor"} className={"text input-group-addon"} type="text"
                                       value={this.state.vendor}
                                       placeholder={"Microsoft"} onChange={this.onInputChange}/>

                                {/* Product field */}
                                <Popup content='Name of the software/application to be tested'
                                       trigger={<label className={"field-label input-group-addon"}> Product
                                           <FontAwesomeIcon icon={faQuestionCircle} className={"qmark"}/>
                                           <FontAwesomeIcon icon={faAsterisk} className={"asterix"}/>
                                       </label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input name={"product"} className={"text input-group-addon"} type="text"
                                       value={this.state.product}
                                       placeholder={"Excel"} onChange={this.onInputChange} />

                                {/* Version field */}
                                <Popup content='The version no. of the application, if known.'
                                       trigger={<label className={"field-label input-group-addon"}> Version number
                                           <FontAwesomeIcon icon={faQuestionCircle} className={"qmark"}/>
                                       </label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input name={"version"} className={"text input-group-addon"} type="text"
                                       value={this.state.version}
                                       placeholder={"2016"} onChange={this.onInputChange}/>
                            </form>

                            <form>
                                <Popup content='Security - The ability of the software to remain protected against the hazards'
                                       trigger={<label className={"field-label input-group-addon"}> Security
                                           <FontAwesomeIcon icon={faQuestionCircle} className={"qmark"}/>
                                       </label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input name="percentage" id={"security"} className={"percentage input-group-addon"}
                                       type="text"
                                       pattern="[0-9]*" maxLength={3} value={this.state.value}
                                       placeholder={"20"} onInput={findTotal} onChange={this.setPercentage}/>
                                       
                                <Popup content='Safety - The ability of the software to operate without causing harm to anything or anyone'
                                       trigger={<label className={"field-label input-group-addon"}> Safety<FontAwesomeIcon
                                           icon={faQuestionCircle}
                                           className={"qmark"}/> </label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input name="percentage" id={"safety"} className={"percentage input-group-addon"}
                                       type="text"
                                       pattern="[0-9]*" maxLength={3} value={this.state.value}
                                       placeholder={"20"} onInput={findTotal} onChange={this.setPercentage}/>
    
                                <Popup content='Resilience -The ability of the software to recover from errors quickly and completely.'
                                       trigger={<label className={"field-label input-group-addon"}> Resiliency <FontAwesomeIcon
                                           icon={faQuestionCircle}
                                           className={"qmark"}/></label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                
                                <input name="percentage" id={"resiliency"} className={"percentage input-group-addon"}
                                       type="text"
                                       pattern="[0-9]*" maxLength={3} value={this.state.value}
                                       placeholder={"20"} onInput={findTotal} onChange={this.setPercentage}/>
    
                                <Popup content='Reliability - The ability of the software to operate correctly.'
                                       trigger={<label className={"field-label input-group-addon"}> Reliability <FontAwesomeIcon
                                           icon={faQuestionCircle}
                                           className={"qmark"}/></label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                
                                <input name="percentage" id={"reliability"} className={"percentage input-group-addon"}
                                       type="text"
                                       pattern="[0-9]*" maxLength={3} value={this.state.value}
                                       placeholder={"20"} onInput={findTotal} onChange={this.setPercentage}/>
    
                                <Popup content='Availability - The ability of the software to operate when required.'
                                       trigger={ <label className={"field-label input-group-addon"}> Availability <FontAwesomeIcon
                                           icon={faQuestionCircle}
                                           className={"qmark"}/></label>
                                       }
                                       className={"popup"}
                                       offset={[-230,0]}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input name="percentage" id={"availability"} className={"percentage input-group-addon"}
                                       type="text" pattern="[0-9]*" maxLength={3} value={this.state.value}
                                       placeholder={"20"} onInput={findTotal} onChange={this.setPercentage}/>

                            </form>
                            <Button disabled={!this.requiredFields()}
                                    style={this.requiredFields() ? {opacity: 1} : {opacity: 0.5}}
                                    className={"btn input-group-addon"} onClick={this.onSubmitForm}>
                                Submit
                            </Button>
                        </div>
                    </div>
                </div>
            </div>

        );
    }
}

export default Home