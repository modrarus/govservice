import React, {Component} from "react";
import ReactDOM from "react-dom";
import GovServices from "./services";

class App extends Component {
	render() {
		return <GovServices/>
	}
}

ReactDOM.render(<App />, document.getElementById('react-main'))