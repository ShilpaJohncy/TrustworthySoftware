import React from "react";

class Documentation extends React.Component {
    render() {
        return (
            <div className="contents">
                <h1>About Trustworthy Analyser</h1>
                Trustworthy analyser is a tool intended to test the trustworthiness of an application based on 5 facets:
                <ul>
                    <p> 1. Security</p>
                    <p> 2. Safety</p>
                    <p> 3. Resiliency</p>
                    <p> 4. Reliability</p>
                    <p> 5. Availability</p>
                </ul>
                Each facet is tested based on multiple features, all of which are weighted based on the confidence level in the results and the feature's contribution to each facet.
            </div>

        );
    }
}

export default Documentation