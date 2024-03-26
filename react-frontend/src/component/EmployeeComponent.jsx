import React, {Component} from "react";
import EmployeeService from "../service/EmployeeService";

class EmployeeComponent extends Component {
    constructor(props) {
        this.state = {
            employee: {},
            department: {},
            organization: {}
        }
    }

    componentDidMount(){
        EmployeeService.getEmployee().then((result) => {
            this.setState({employee: result.employee})
            this.setState({department: result.department})
            this.setState({organization: result.organization})
            console.log(this.state.employee)
            console.log(this.state.department)
            console.log(this.state.organization)
        })
    }

    render(){
        return(
            <div>

            </div>
        )
    }
}

export default EmployeeComponent;