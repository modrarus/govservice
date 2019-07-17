import React, {Component} from "react";
import { Link } from 'react-router-dom';
import axios from 'axios';

class GovServices extends Component {
    constructor(props) {
        super(props);
        this.state = {services : [], loading : true};
    }
    
    componentDidMount() {
        this.setState({services : [], loading : true});

        axios.get('api/list')
            .then(data => this.setState({services : data.data, loading : false}))
            .catch(error => console.log(error.data));
    }
    
    render() {
        if (this.state.loading == true) {
            return <p>Загрузка...</p>;
        } else if (this.state.error != null) {
            return <p>Ошибка загрузки списка услуг!</p>
        }

        const govServicesList = this.state.services.map(govService => {
            return <GovService service={govService} />
        })
        return <table>
            <thead>
                <tr>
                    <td>Название услуги</td>
                    <td>Описание</td>
                </tr>
            </thead>
            <tbody>
                {govServicesList}
            </tbody>
        </table>
    }
}

class GovService extends Component {
    render() {
        return (<tr>
            <td>{this.props.service.name}</td>
            <td>{this.props.service.description}</td>
            <td><Link to={`/request/${this.props.service.id}`}>Запросить</Link></td>
        </tr>)
    }
}

export default GovServices;