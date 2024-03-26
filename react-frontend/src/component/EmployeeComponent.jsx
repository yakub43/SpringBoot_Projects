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
        // EmployeeService.getEmployee().then((res) =>{
        //     this.setState({employee: res.employee}),
        //     this.setState({department: res.department}),
        //     this.setState({organization: res.organization}),

        //     console.log(this.state.employee);
        // })
    }

    render(){
        return(
            <div>

            </div>
        )
    }
}

export default EmployeeComponent;