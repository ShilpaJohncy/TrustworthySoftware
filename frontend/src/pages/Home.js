import React from "react";
import {faFolder} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            vendor:'',
            product:'',
            version:'',
            exe:'',
            safety:'',
            security:'',
            availability:'',
            resiliency:'',
            reliability:''
        };
        this.onInputChange = this.onInputChange.bind(this);
        this.onSubmitForm = this.onSubmitForm.bind(this);
    }

    onInputChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    async onSubmitForm(event) {
        const object = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(this.state)
        }

        const response = await fetch('submit', object);
        await response.json().then(data =>
            alert("Verdict " + JSON.stringify(data))
        )
        event.preventDefault();
    }

    render() {
        return (
            <div className="contents">
                <h1>Trustworthy Analyser</h1>
                <div className="wrapper">
                    <div className="box">
                        <div>
                            <form>
                                <label className={"btn input-group-addon"}> <FontAwesomeIcon icon={faFolder} className={"icon"}/>
                                    <input type="file" value={this.state.value} onChange={this.onInputChange} accept={".exe"}/>
                                    Browse
                                </label>
                                <input name={"exe"} className={"file-browse input-group-addon"} type="text" value={this.state.value}
                                       placeholder={"Choose application"} onChange={this.onInputChange}/>
                            </form>

                            <form>
                                {/* Vendor field */}
                                <label className={"field-label input-group-addon"}> Vendor </label>
                                <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputChange}/>
                                <input name={"vendor"} className={"text input-group-addon"} type="text" value={this.state.vendor}
                                       placeholder={"Microsoft"} onChange={this.onInputChange}/>

                                {/* Product field */}
                                <label className={"field-label input-group-addon"}> Product </label>
                                <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputChange}/>
                                <input name={"product"} className={"text input-group-addon"} type="text" value={this.state.product}
                                       placeholder={"Excel"} onChange={this.onInputChange}/>

                                {/* Version field */}
                                <label className={"field-label input-group-addon"}> Version number </label>
                                <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputChange}/>
                                <input name={"version"} className={"text input-group-addon"} type="text" value={this.state.version}
                                       placeholder={"2016"} onChange={this.onInputChange}/>

                            </form>

                            <form>

                                <label className={"field-label input-group-addon"}> Security </label>
                                <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputChange}/>
                                <input name={"security"} className={"percentage input-group-addon"} type="number" min={0} max={100} value={this.state.security}
                                       placeholder={"20"} onChange={this.onInputChange}/>


                                <label className={"field-label input-group-addon"}> Safety </label>
                                <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputChange}/>
                                <input name={"safety"} className={"percentage input-group-addon"} type="number" min={0} max={100}  value={this.state.safety}
                                       placeholder={"20"} onChange={this.onInputChange}/>

                                <label className={"field-label input-group-addon"}> Resiliency </label>
                                <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputChange}/>
                                <input name={"resiliency"} className={"percentage input-group-addon"} type="number" min={0} max={100} value={this.state.resiliency}
                                       placeholder={"20"} onChange={this.onInputChange}/>

                                <label className={"field-label input-group-addon"}> Reliability </label>
                                <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputChange}/>
                                <input name={"reliability"} className={"percentage input-group-addon"} type="number"min={0} max={100}  value={this.state.reliability}
                                       placeholder={"20"} onChange={this.onInputChange}/>

                                <label className={"field-label input-group-addon"}> Availability </label>
                                <input type={"fieldLabel"} value={this.state.value} onChange={this.onInputChange}/>
                                <input name={"availability"} className={"percentage input-group-addon"} type="number" min={0} max={100} value={this.state.availability}
                                       placeholder={"20"} onChange={this.onInputChange}/>

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