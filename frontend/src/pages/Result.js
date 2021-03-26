import React, {Component} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faQuestionCircle} from "@fortawesome/free-solid-svg-icons";
import {Popup} from "semantic-ui-react";
import Loader from 'react-loader-spinner';
import Home from "./Home";

class Result extends Component {
    constructor(props) {
        super(props);
        this.props = props;
        this.state = {
            redirect: false,
            data:this.props.message,
            result:[]

        };
        this.onReturn = this.onReturn.bind(this)
        this.fetchResults = this.fetchResults.bind(this)
    }
    onReturn(){
        this.setState({ redirect: true });
    }

    componentDidMount() {
        this.fetchResults()
    }

    fetchResults = () => {
        const object = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: this.state.data
        }
        this.setState({...this.state, isFetching: true})
        fetch('submit', object)
            .then(response => response.json())
            .then(result => this.setState({result: (result),
                isFetching: false}))
            .catch(e => console.log(e));
    }
    render() {
        if (this.state.redirect) {
            return <Home />
        }
        return (<div className="contents">
                <h1>Trustworthy Analyser Verdict</h1>
                <div className="wrapper">
                    <div className="box">
                        <div> {(this.state.isFetching) ?
                            <div> <p className={"fetch"}> Fetching results </p>
                                <Loader type="ThreeDots" color="black"/>
                            </div> : <div>
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
                                           pattern="[0-9]*" maxLength={3} value={JSON.stringify(this.state.result.securityScore)}
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
                                           pattern="[0-9]*" maxLength={3} value={JSON.stringify(this.state.result.safetyScore)}
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
                                           pattern="[0-9]*" maxLength={3} value={JSON.stringify(this.state.result.resilienceScore)}
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
                                           pattern="[0-9]*" maxLength={3} value={JSON.stringify(this.state.result.reliabilityScore)}
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
                                           type="text" pattern="[0-9]*" maxLength={3} value={JSON.stringify(this.state.result.availabilityScore)}
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
                                           pattern="[0-9]*" maxLength={3} value={JSON.stringify(this.state.result.trustworthyScore)}
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
                                           type="text" value={(this.state.result.verdict)} disabled/>
                                </form>
                                <input value={"Run another"}type={"submit"} className={"btn input-group-addon"} onClick={this.onReturn}/>

                            </div>
                        }
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Result