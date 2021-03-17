import React from "react";
import { faFolder } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

class Home extends React.Component {
    constructor() {
        super();
        this.state = {
        };
        this.onInputchange = this.onInputchange.bind(this);
        this.onSubmitForm = this.onSubmitForm.bind(this);
    }

    onInputchange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    onSubmitForm() {
        console.log(this.state)
        alert("Submitted")
    }


    render() {
        return (
            <div className="contents">
                <h1>Trustworthy Analyser</h1>
                <p> Trustworthy Analyser is a free service which performs static analysis on PE executables to help determine its trustworthiness.</p>
                <p> This tool is only meant to help aid the user in making a decision, and is not responsible for any choice made by the user!</p>

                <div className="wrapper">
                        <div className="box">
                            <div>
                                <form>
                                    <label className={"btn input-group-addon"}>
                                        <input name={"exe"} type="file" value={this.state.value} onChange={this.onInputchange}
                                               accept={".exe"}/><FontAwesomeIcon icon={faFolder} className={"icon"} />
                                        Browse
                                    </label>
                                    <input className={"file-browse input-group-addon"} type="text" value={this.state.exe}
                                           placeholder={"Choose application"} onChange={this.onInputchange} />
                                </form>

                                <form>
                                    <label className={"field-label input-group-addon"}>
                                        <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputchange}/>
                                        Vendor
                                    </label>
                                    <input className={"text input-group-addon"} type="text" value={this.state.value}
                                           placeholder={"Microsoft"} onChange={this.onInputchange}/>

                                {/*</form>*/}
                                {/*<form>*/}
                                    <label className={"field-label input-group-addon"}>
                                        <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputchange}/>
                                        Product
                                    </label>
                                    <input className={"text input-group-addon"} type="text" value={this.state.value}
                                           placeholder={"Excel"} onChange={this.onInputchange}/>

                                {/*</form>*/}
                                {/*<form>*/}
                                    <label className={"field-label input-group-addon"}>
                                        <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputchange}/>
                                        Version number
                                    </label>
                                    <input className={"text input-group-addon"} type="text" value={this.state.value}
                                           placeholder={"2016"} onChange={this.onInputchange}/>
                                </form>

                                <form>
                                    <label className={"field-label input-group-addon"}>
                                        <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputchange}/>
                                        Security
                                    </label>
                                    <input className={"percentage input-group-addon"} type="text" value={this.state.value}
                                           placeholder={"20"} onChange={this.onInputchange}/>

                                    <label className={"field-label input-group-addon"}>
                                        <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputchange}/>
                                        Safety
                                    </label>
                                    <input className={"percentage input-group-addon"} type="text" value={this.state.value}
                                           placeholder={"20"} onChange={this.onInputchange}/>

                                    <label className={"field-label input-group-addon"}>
                                        <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputchange}/>
                                        Resiliency
                                    </label>
                                    <input className={"percentage input-group-addon"} type="text" value={this.state.value}
                                           placeholder={"20"} onChange={this.onInputchange}/>

                                    <label className={"field-label input-group-addon"}>
                                        <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputchange}/>
                                        Reliability
                                    </label>
                                    <input className={"percentage input-group-addon"} type="text" value={this.state.value}
                                           placeholder={"20"} onChange={this.onInputchange}/>

                                    <label className={"field-label input-group-addon"}>
                                        <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputchange}/>
                                        Availability
                                    </label>
                                    <input className={"percentage input-group-addon"} type="text" value={this.state.value}
                                           placeholder={"20"} onChange={this.onInputchange}/>
                                </form>
                                <input type={"submit"} className={"btn input-group-addon"} onClick={this.onSubmitForm}/>
                            </div>
                        </div>
                </div>
            </div>

        );
    }
}

export default Home