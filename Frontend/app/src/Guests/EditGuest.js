import React, {Component} from 'react'
import AppNav from "../AppNav";
import '../App.css';
import {Container, Form, FormGroup, Input, Label, Button} from 'reactstrap';
import {Link} from 'react-router-dom';

export default class EditGuest extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoading: true,
            guest: []
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    async handleSubmit(event) {
        event.preventDefault();
        const guest = this.state.guest;
        await fetch('/account', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(guest),
        }).then((response) => {
            if (response.ok) {
                this.props.history.push("/home");
            }
        });
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let guest = {...this.state.guest};
        guest[name] = value;
        this.setState({guest});
    }

    async componentDidMount() {
        const response = await fetch(`/account/${this.props.match.params.id}`);
        const body = await response.json();
        this.setState({guest: body, isLoading: false});
    }


    render() {

        const {guest, isLoading} = this.state;

        if (isLoading)
            return (<div>Loading...</div>)

        return (
            <div><AppNav/>
                <h2 className="text-center mt-5">Edit Guest</h2>
                <div className="container">
                    <div className="row">
                        <div className="col"/>
                        <div className="col">
                            <Container className="mt-5">
                                <Form onSubmit={this.handleSubmit}>
                                    <FormGroup>
                                        <Label for="firstName">First Name</Label>
                                        <Input type="text" name="firstName" id="firstName" value={guest.firstName}
                                               onChange={this.handleChange}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="lastName">Last Name</Label>
                                        <Input type="text" name="lastName" id="lastName" value={guest.lastName}
                                               onChange={this.handleChange}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="licensePlate">License Plate</Label>
                                        <Input type="text" name="licensePlate" id="licensePlate"
                                               value={guest.licensePlate} onChange={this.handleChange}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="phoneNumber">Phone Number</Label>
                                        <Input type="text" name="phoneNumber" id="phoneNumber" value={guest.phoneNumber}
                                               onChange={this.handleChange}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Button color="primary" type="submit">Save</Button>{' '}
                                        <Button color="secondary" tag={Link} to="/home">Cancel</Button>
                                    </FormGroup>
                                </Form>
                            </Container>
                        </div>
                        <div className="col"></div>
                    </div>
                </div>
            </div>);
    }
}
