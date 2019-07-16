import React, {Component} from "react";

class GovServices extends Component {
    constructor(props) {
        super(props);
        this.state = {services : [], loading : false, error : null};
    }
    
    componentDidMount() {
        this.setState({services : [], loading : true, error : null});

        fetch('api/list')
            .then(response => response.json())
            .then(data => this.setState({services : data, loading : false, error : null}))
    }
    
    render() {
        if (this.state.loading == true) {
            return <p>Загрузка...</p>;
        }

        let govServicesList = this.state.services.map(govService =>{
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
    request() {
        alert(this.props.service.id);
    }

    render() {
        return <tr onClick={() => this.request()}>
            <td>{this.props.service.name}</td>
            <td>{this.props.service.description}</td>
        </tr>
    }
}

export default GovServices;