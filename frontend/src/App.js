import React from "react";
import ReactDOM from "react-dom"
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./pages/Home";
import About from "./pages/About";
import {Link} from "react-router-dom";

const App = () => {
    const [highlightTopPosition, setStateHighlightTopPosition] = React.useState(0);
    const [currCount, setCurrCount] = React.useState(0);

    const onClickTab = (count) => {
        setCurrCount(count);
        setStateHighlightTopPosition(count * 52);
        setTimeout(() => {
        }, 100);
    }

    return (
        <Router>
            <main>
                <nav>
                    <div className="container">
                        <div className="navbar">
                            <div style={{top: `${highlightTopPosition}px`}}/>

                            <Link className={currCount === 0 && 'active'} onClick={() => onClickTab(0)} to="/">
                                <span className={currCount === 0 && 'text-active'}> Home</span>
                            </Link>

                            <Link className={currCount === 1 && 'active'} onClick={() => onClickTab(1)} to="/About">
                                <span className={currCount === 1 && 'text-active'}> About</span>
                            </Link>

                            <Link className={currCount === 2 && 'active'} onClick={() => onClickTab(2)} to="/About">
                                <span className={currCount === 2 && 'text-active'}>Documentation</span>
                            </Link>

                            <Link className={currCount === 3 && 'active'} onClick={() => onClickTab(3)} to="/About">
                                <span className={currCount === 3 && 'text-active'}>Contact</span>
                            </Link>
                        </div>
                    </div>

                    <div className="content">
                        <Switch>
                            <Route exact path="/">
                                <Home />
                            </Route>
                            <Route path="/About">
                                <About />
                            </Route>
                        </Switch>
                    </div>

                </nav>
            </main>
        </Router>
    )
}


ReactDOM.render(<App />, document.getElementById("root"));
export default App