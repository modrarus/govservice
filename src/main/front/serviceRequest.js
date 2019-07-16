import React, {Component} from "react";

class GovServiceRequest extends Component {
    constructor(props) {
        super(props);
        this.state = {fields : null, loading : true, error : null};
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        alert("submited");
    }

    componentDidMount() {
        this.state = {fields : null, loading : true, error : null};
        
        const requestComponent = this;
        fetch(`/api/${this.props.match.params.id}/schema`)
            .then(response => response.json())
            .then(data => this.setState({fields : data, loading : false, error : null}))
    }

    render () {
        if (this.state.loading == true) {
            return <p>Загрузка...</p>
        }

        const labelStyle = {
            display: "block",
          };

        const fields = this.state.fields.map(field => {
            return <label style={labelStyle}>
                    {field.displayName}: <br />
                    <input type="text" name={field.name} />
                </label>
        });

        return (
            <form onSubmit={this.handleSubmit}>
                {fields}
                <input type="submit" value="Запросить услугу"/>
            </form>
        )
    }
}

export default GovServiceRequest;