import React, {Component} from "react";
import { Link } from 'react-router-dom';

class GovServiceRequest extends Component {
    constructor(props) {
        super(props);
        this.state = {schema : [], values : {}, loading : true, error : null};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const requestData = this.state.values;

        fetch(`/api/${this.props.match.params.id}/request`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData),
            credentials: 'include'
        });

        alert("submited");
    }

    handleInputChange(event) {
        let values = this.state.values;
        values[event.target.name] = event.target.value;
        this.setState({values : values});
    }

    componentDidMount() {
        this.setState({schema : [], values:{}, loading : true, error : null});
        
        fetch(`/api/${this.props.match.params.id}/schema`)
            .then(response => response.json())
            .then(data => this.setState({schema : data, values:{}, loading : false, error : null}))
    }

    render () {
        if (this.state.loading == true) {
            return <p>Загрузка...</p>
        }

        const labelStyle = {
            display: "block",
          };

        const fields = this.state.schema.map(field => {
            return <label style={labelStyle}>
                    {field.displayName}: <br />
                    <input type="text" name={field.name} onChange={this.handleInputChange}/>
                </label>
        });

        return (
            <div>
                <Link to="/">К списку услуг</Link><br />

                <form onSubmit={this.handleSubmit}>
                    {fields}
                    <input type="submit" value="Запросить услугу"/>
                </form>
            </div>
        )
    }
}

export default GovServiceRequest;