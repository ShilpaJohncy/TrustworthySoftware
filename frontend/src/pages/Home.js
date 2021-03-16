import React from "react";

class Home extends React.Component {
    constructor() {
        super();
        this.state = {};
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
    }

    render() {
        return (

            <div className="contents">
                <h1>Trustworthy Analyser</h1>
                <p> Trustworthy Analyser is a free service which performs static analysis on PE executables to help determine its trustworthiness.</p>
                <p> This tool is only meant to help aid the user in making a decision, and is not responsible for any choice made by the user!</p>

                <div>
                    <form>
                        <label className={"btn input-group-addon"}>
                            <input name={"exe"} type="file" value={this.state.value} onChange={this.onInputchange}
                                   accept={".exe"}/>
                            Browse
                        </label>

                        <label >
                            <input className={"text input-group-addon"} type="text" value={this.state.exe}
                                   placeholder={"Choose the application to be analysed"} onChange={this.onInputchange}
                                   disabled/>
                        </label>
                    </form>
                </div>
            </div>

        );
    }
}

export default Home