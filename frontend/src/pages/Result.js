import React, {Component} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faQuestionCircle} from "@fortawesome/free-solid-svg-icons";
import {Popup} from "semantic-ui-react";
import Home from "./Home";

class Result extends Component {
    constructor(props) {
        super(props);
        this.props = props;
        this.state = {
            redirect: null,
            data:[]
        };
        this.onReturn = this.onReturn.bind(this)
    }
    onReturn(){
        this.setState({ redirect: "/" });
    }

    render() {
        this.state.data = (this.props.message);
        if (this.state.redirect) {
            return <Home />
        }
        return (
        <div className="contents">
            <h1>Trustworthy Analyser Verdict</h1>
            <div className="wrapper">
                <div className="box">
                    <div>
                        <form>
                            <Popup content='Security - The ability of the software to remain protected against the hazards'
                                   trigger={<label className={"field-label input-group-addon"}> Security
                                       <FontAwesomeIcon icon={faQuestionCircle} className={"qmark"}/>
                                   </label>}
                                   className={"popup"}
                                   mouseEnterDelay={1000}
                                   mouseLeaveDelay={500}/>
                            <input id={"security"} className={"percentage input-group-addon"}
                                   type="text"
                                   pattern="[0-9]*" maxLength={3} value={JSON.parse(this.state.data).securityScore}
                                   placeholder={"0"} disabled/>

                            <Popup content='Safety - The ability of the software to operate without causing harm to anything or anyone'
                                   trigger={<label className={"field-label input-group-addon"}> Safety<FontAwesomeIcon
                                       icon={faQuestionCircle}
                                       className={"qmark"}/> </label>}
                                   className={"popup"}
                                   mouseEnterDelay={1000}
                                   mouseLeaveDelay={500}/>
                            <input name="percentage" id={"safety"} className={"percentage input-group-addon"}
                                   type="text"
                                   pattern="[0-9]*" maxLength={3} value={JSON.parse(this.state.data).safetyScore}
                                   placeholder={"0"} disabled/>

                            <Popup content='Resilience -The ability of the software to recover from errors quickly and completely.'
                                   trigger={<label className={"field-label input-group-addon"}> Resiliency <FontAwesomeIcon
                                       icon={faQuestionCircle}
                                       className={"qmark"}/></label>}
                                   className={"popup"}
                                   mouseEnterDelay={1000}
                                   mouseLeaveDelay={500}/>

                            <input name="percentage" id={"resiliency"} className={"percentage input-group-addon"}
                                   type="text"
                                   pattern="[0-9]*" maxLength={3} value={JSON.parse(this.state.data).resilienceScore}
                                   placeholder={"0"} disabled/>

                            <Popup content='Reliability - The ability of the software to operate correctly.'
                                   trigger={<label className={"field-label input-group-addon"}> Reliability <FontAwesomeIcon
                                       icon={faQuestionCircle}
                                       className={"qmark"}/></label>}
                                   className={"popup"}
                                   mouseEnterDelay={1000}
                                   mouseLeaveDelay={500}/>

                            <input name="percentage" id={"reliability"} className={"percentage input-group-addon"}
                                   type="text"
                                   pattern="[0-9]*" maxLength={3} value={JSON.parse(this.state.data).reliabilityScore}
                                   placeholder={"0"} disabled/>

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
                                   type="text" pattern="[0-9]*" maxLength={3} value={JSON.parse(this.state.data).availabilityScore}
                                   placeholder={"0"} disabled/>

                        </form>
                        <form>
                            <Popup content='Trustworthy Score'
                                   trigger={ <label className={"field-label input-group-addon"}> Trustworthy score <FontAwesomeIcon
                                       icon={faQuestionCircle}
                                       className={"qmark"}/></label>
                                   }
                                   className={"popup"}
                                   offset={[-230,0]}
                                   mouseEnterDelay={1000}
                                   mouseLeaveDelay={500}/>
                            <input name="score" className={"percentage input-group-addon"} type="text"
                                   pattern="[0-9]*" maxLength={3} value={JSON.parse(this.state.data).trustworthyScore}
                                   placeholder={"0"} disabled/>

                        </form>
                        <form>
                            <Popup content='Trustworthy Verdict'
                                   trigger={<label className={"field-label input-group-addon"}> Verdict
                                       <FontAwesomeIcon icon={faQuestionCircle} className={"qmark"}/>
                                   </label>}
                                   className={"popup"}
                                   mouseEnterDelay={1000}
                                   mouseLeaveDelay={500}/>
                            <input id={"verdict"} className={"verdict input-group-addon"}
                                   type="text" value={JSON.parse(this.state.data).verdict} disabled/>
                        </form>
                        <input value={"Run another"}type={"submit"} className={"btn input-group-addon"} onClick={this.onReturn}/>

                    </div>
                </div>
            </div>
        </div>


    );
    }
}

export default Result