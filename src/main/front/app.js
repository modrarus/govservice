import React, {Component} from "react";
import ReactDOM from "react-dom";

class App extends Component {
	render() {
		return <p>Hello, world!</p>
	}
}

ReactDOM.render(<App />, document.getElementById('react-main'))