import React from "react";
import ReactDOM from "react-dom"
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./pages/Home";
import Documentation from "./pages/Documentation";
import {Link} from "react-router-dom";
import Result from "./pages/Result";

const App = () => {
    const [currCount, setCurrCount] = React.useState(0);

    const onClickTab = (count) => {
        setCurrCount(count);
        setTimeout(() => {
        }, 100);
    }

    return (
        <Router>
            <main className={"bg"}>
                <nav className="navbar navbar-default navbar-static-top">
                    <div className="container">
                        <div className="navbar">
                            <Link className={currCount === 0 && 'active ' } onClick={() => onClickTab(0)} to="/">
                                <span className={currCount === 0 && 'text-active'}> Home</span>
                            </Link>

                            <Link className={currCount === 1 && 'active'} onClick={() => onClickTab(1)} to="/Documentation">
                                <span className={currCount === 1 && 'text-active'}> Documentation</span>
                            </Link>
                        </div>
                    </div>

                    <Switch>
                        <Route exact path="/">
                            <Home />
                        </Route>
                        <Route path="/Documentation">
                            <Documentation />
                        </Route>
                        <Route path="/Verdict">
                            <Result />
                        </Route>
                    </Switch>

                </nav>
            </main>
        </Router>
    )
}


ReactDOM.render(<App />, document.getElementById("root"));
export default App