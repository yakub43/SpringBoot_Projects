import React, {Component} from "react";
import EmployeeService from "../service/EmployeeService";
import axios from 'axios';

const EMPLOYEE_SERVICE_BASE_URL = "http://localhost:9191/api/employees";
const EMPLOYEE_ID = 4;

class EmployeeComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            employee: {},
            department: {},
            organization: {}
        }
    }

    componentDidMount(){
         axios.get(EMPLOYEE_SERVICE_BASE_URL + '/' + EMPLOYEE_ID).then((res)=>
{         this.setState({employee: res.data.employeeDto}),
         this.setState({department: res.data.departmentDto}),
         this.setState({organization: res.data.organizationDto}),

         console.log(this.state.employee)}
         )
    }

    render(){
        return(
            <div>
            <div className="card offset-md-3" >
                <h3 className="text-center">View Employee Card</h3>
                <div className="card-body" >
                    <div className="row" >
                        <p><strong>Employee First Name: </strong>{this.state.employee.firstName}</p>
                    </div>
                    <div className="row" >
                        <p><strong>Employee Last Name: </strong>{this.state.employee.lastName}</p>
                    </div>
                    <div className="row" >
                        <p><strong>Employee Email: </strong>{this.state.employee.email}</p>
                    </div>
                </div>
                <h3 className="text-center card-header" >Vie Department Details</h3>
                <div className="row" >
                    <p><strong>Department Name: </strong>{this.state.department.departmentName}</p>
                </div>
                <div className="row" >
                    <p><strong>Department Description: </strong>{this.state.department.departmentDescription}</p>
                </div>
                <div className="row" >
                    <p><strong>Department Code: </strong>{this.state.department.departmentCode}</p>
                </div>
            </div>
            </div>
        )
    }
}

export default EmployeeComponent;