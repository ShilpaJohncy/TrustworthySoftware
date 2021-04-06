import React, {Component} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faQuestionCircle} from "@fortawesome/free-solid-svg-icons";
import {Button, Popup} from "semantic-ui-react";
import Loader from 'react-loader-spinner';
import Home from "./Home";
import Rating from '@material-ui/lab/Rating';

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
        return (
            <div className="contents">
                {
                    (this.state.isFetching) ?
                        <h1> Analysing product
                            <Loader type="ThreeDots" color="black" height="25" width="40" />
                        </h1>:
                        <h1>Trustworthy Analyser Verdict
                            <Loader height="0" width="0" />
                        </h1>
                }

                <div className="wrapper">
                    <div className="box">
                        <div>
                            <form>
                                <Popup content='Security score of the application'
                                       trigger={<label className={"field-label input-group-addon"}> Security
                                           <FontAwesomeIcon icon={faQuestionCircle} className={"qmark"}/>
                                       </label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input name="percentage"  id={"security"} className={"percentage input-group-addon"}
                                       type="text" value={(this.state.isFetching) ? "": (this.state.result.securityScore  + "%") }
                                       placeholder={"0"} disabled/>

                                <Popup content='Safety score of the application'
                                       trigger={<label className={"field-label input-group-addon"}> Safety<FontAwesomeIcon
                                           icon={faQuestionCircle}
                                           className={"qmark"}/> </label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input name="percentage" id={"safety"} className={"percentage input-group-addon"}
                                       type="text"
                                       pattern="[0-9]*" maxLength={3} value={(this.state.isFetching) ? "": (this.state.result.safetyScore  + "%")}
                                       placeholder={"0"} disabled/>

                                <Popup content='Resiliency score of the application'
                                       trigger={<label className={"field-label input-group-addon"}> Resiliency <FontAwesomeIcon
                                           icon={faQuestionCircle}
                                           className={"qmark"}/></label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>

                                <input name="percentage" id={"resiliency"} className={"percentage input-group-addon"}
                                       type="text"
                                       pattern="[0-9]*" maxLength={3} value={(this.state.isFetching) ? "": (this.state.result.resiliencyScore + "%")}
                                       placeholder={"0"} disabled/>

                                <Popup content='Reliability score of the application'
                                       trigger={<label className={"field-label input-group-addon"}> Reliability <FontAwesomeIcon
                                           icon={faQuestionCircle}
                                           className={"qmark"}/></label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>

                                <input name="percentage" id={"reliability"} className={"percentage input-group-addon"}
                                       type="text"
                                       pattern="[0-9]*" maxLength={3} value={(this.state.isFetching) ? "": (this.state.result.reliabilityScore + "%")}
                                       placeholder={"0"} disabled/>

                                <Popup content='Availability score of the application'
                                       trigger={ <label className={"field-label input-group-addon"}> Availability <FontAwesomeIcon
                                           icon={faQuestionCircle}
                                           className={"qmark"}/></label>
                                       }
                                       className={"popup"}
                                       offset={[-230,0]}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input name="percentage" id={"availability"} className={"percentage input-group-addon"}
                                       type="text" pattern="[0-9]*" maxLength={3} value={(this.state.isFetching) ? "": (this.state.result.availabilityScore + "%")}
                                       placeholder={"0"} disabled/>

                            </form>
                            <form>
                                <Popup content='Trustworthiness score calculated based on the weighted scores of each of the above facets'
                                       trigger={ <label className={"field-label input-group-addon"}> Trustworthy Points<FontAwesomeIcon
                                           icon={faQuestionCircle}
                                           className={"qmark"}/></label>
                                       }
                                       className={"popup"}
                                       offset={[0,0]}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                       <label className={"verdict input-group-addon"} placeholder={"0%"} >
                                           {(this.state.isFetching) ? "   ": (this.state.result.trustworthyScore + "  ")}

                                           {/*<input name="score"  className={" percentage input-group-addon"} type="text"*/}
                                           {/*       pattern="[0-9]*" maxLength={3} }*/}
                                           {/*       placeholder={"0"} disabled/>*/}
                                           <Rating className={"rating"}
                                                   size="small"
                                                   value={
                                                       (this.state.isFetching) ? 0 : (
                                                           (this.state.result.trustworthyScore < 20) ? 1  : (
                                                               (this.state.result.trustworthyScore < 40) ? 2 : (
                                                                   (this.state.result.trustworthyScore < 60) ? 3 :(
                                                                       (this.state.result.trustworthyScore < 80) ? 4 : 5
                                                                   )
                                                               )
                                                           )
                                                       )
                                                   }
                                                   readOnly={true}/>
                                       </label>

                            </form>
                            <form>
                                <Popup content='Final verdict of trustworthiness based on a 5star scale'
                                       trigger={<label className={"field-label input-group-addon"}> Verdict
                                           <FontAwesomeIcon icon={faQuestionCircle} className={"qmark"}/>
                                       </label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input id={"verdict"} className={"verdict input-group-addon"}
                                       type="text" placeholder={"Verdict"} value={(this.state.result.verdict)} disabled/>

                                <Popup content='Confidence level in the verdict'
                                       trigger={<label className={"field-label input-group-addon"}> Confidence in Verdict
                                           <FontAwesomeIcon icon={faQuestionCircle} className={"qmark"}/>
                                       </label>}
                                       className={"popup"}
                                       mouseEnterDelay={1000}
                                       mouseLeaveDelay={500}/>
                                <input id={"confidence"} className={"percentage input-group-addon"}
                                       type="text" placeholder={"Confidence in the result"} value={"100%"} disabled/>


                            </form>

                            <Button disabled={this.state.isFetching}
                                    style={!this.state.isFetching ? {opacity: 1} : {opacity: 0.5}}
                                    className={"btn input-group-addon"} onClick={this.onReturn}>
                                Run another
                            </Button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Result