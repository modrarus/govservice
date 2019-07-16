import React, {Component} from "react";
import ReactDOM from "react-dom";
import GovServices from "./services";
import GovServiceRequest from "./serviceRequest";
import { BrowserRouter, Route, Switch } from "react-router-dom"

class App extends Component {
	render() {
		return (
			<BrowserRouter>
				<Switch>
					<Route exact path='/' component={GovServices}/>
					<Route path='/request/:id' component={GovServiceRequest}/>
				</Switch>
			</BrowserRouter>
		)
	}
}

ReactDOM.render(<App />, document.getElementById('react-main'))