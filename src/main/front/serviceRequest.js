import React, {Component} from "react";
import { Link, withRouter } from 'react-router-dom';
import axios from 'axios';

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

        const router = this.props.history;
        const schema = this.state.schema;
        axios.post(`/api/${this.props.match.params.id}/request`, requestData)
            .then(data => {
                alert('Запроу услуги успешно принят.');
                router.push("/");
            })
            .catch(err => {
                alert(err.response.data.error);
            });
    }

    handleInputChange(event) {
        let values = this.state.values;
        values[event.target.name] = event.target.value;
        this.setState({values : values});
    }

    componentDidMount() {
        this.setState({schema : [], values:{}, loading : true, error : null});
        
        const router = this.props.history;
        axios.get(`/api/${this.props.match.params.id}/schema`)
            .then(data => this.setState({schema : data.data, values:{}, loading : false, error : null}))
            .catch(function(err) {
                alert('Ошибка получения запрошенной услуги. Вы будете перенаправлены на начальную страницу.');
                router.push("/");
            });
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

export default withRouter(GovServiceRequest);