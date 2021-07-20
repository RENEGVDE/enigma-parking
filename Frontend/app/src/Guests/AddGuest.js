import React, { Component } from 'react';
import AppNav from '../AppNav';
import '../App.css';
import { Container, Form, FormGroup, Input, Label, Button } from 'reactstrap';
import { Link } from 'react-router-dom';

class AddGuest extends Component {

    emptyGuest={
        accountId: '',
        licensePlate: '',
        firstName: '',
        lastName: '',
        phoneNumber: '',
        contactViaWhatsapp: false
    };

    constructor(props){
        super(props);

        this.state = {
            guest: this.emptyGuest,
            showFirstNameError: false,
            showLastNameError: false,
            showLicensePlateError: false,
            showPhoneNumberNameError: false
        };

        this.handleSubmit= this.handleSubmit.bind(this);
        this.handleChange= this.handleChange.bind(this);
    }

    async handleSubmit(event){
        event.preventDefault();
        if(this.formValidation()){
            const guest = this.state.guest;
            await fetch('/account', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(guest),
            }).then((response) => {if (response.ok) {
                this.props.history.push("/home");
            }});
        }
    }

    formValidation(){
        let validated = true;

        this.setState({
            showFirstNameError: false,
            showLastNameError: false,
            showLicensePlateError: false,
            showPhoneNumberNameError: false
        });

        const guest = this.state.guest;
        if ( guest.firstName === '' ){
            this.setState({showFirstNameError: true});
            validated = false;
        }
        if ( guest.lastName === '' ){
            this.setState({showLastNameError: true});
            validated = false;
        }
        if ( guest.licensePlate === '' ){
            this.setState({showLicensePlateError: true});
            validated = false;
        }
        if ( guest.phoneNumber === '' ){
            this.setState({showPhoneNumberNameError: true});
            validated = false;
        }
        return validated;
    }

    handleChange(event){
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
        let guest={...this.state.guest};
        guest[name] = value;
        this.setState({guest});
        console.log(guest);
    }

    render() {
        const {showFirstNameError, showLastNameError, showLicensePlateError, showPhoneNumberNameError} = this.state;

        return (
            <div><AppNav/>
                <h2 className="text-center mt-5">Add Guest</h2>
                <div className="container">
                    <div className="row">
                        <div className="col"/>
                        <div className="col">
                            <Container className="mt-5">
                                <Form onSubmit={this.handleSubmit}>
                                    <FormGroup>
                                        <Label for="firstName">First Name</Label>
                                        <Input type="text" name="firstName" id="firstName" onChange={this.handleChange}/>
                                        <p style={{color: 'red'}}>{showFirstNameError ? 'Missing First Name' : ''}</p>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="lastName">Last Name</Label>
                                        <Input type="text" name="lastName" id="lastName" onChange={this.handleChange}/>
                                        <p style={{color: 'red'}}>{showLastNameError ? 'Missing Last Name' : ''}</p>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="licensePlate">License Plate</Label>
                                        <Input type="text" name="licensePlate" id="licensePlate" onChange={this.handleChange}/>
                                        <p style={{color: 'red'}}>{showLicensePlateError ? 'Missing License Plate' : ''}</p>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="phoneNumber">Phone Number</Label>
                                        <Input type="text" name="phoneNumber" id="phoneNumber" onChange={this.handleChange}/>
                                        <p style={{color: 'red'}}>{showPhoneNumberNameError ? 'Incorrect or missing Phone Number' : ''}</p>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="contactviaSMS">Receive notification via Whatsapp?:</Label>
<<<<<<< HEAD
                                        <Input className="ml-2" type="checkbox" name="contactViaWhatsapp" id="contactViaWhatsapp" onChange={this.handleChange}/>                                    
=======
                                        <Input type="checkbox" name="contactViaWhatsapp" id="contactViaWhatsapp" onChange={this.handleChange}/>                                    
>>>>>>> d4e5d18637d9bf51b5992bade8f93de4a9a2ca73
                                    </FormGroup>
                                    <FormGroup>
                                        <Button color="primary" type="submit" >Save</Button>{' '}
                                        <Button color="secondary" tag={Link} to="/home">Cancel</Button>
                                    </FormGroup>
                                </Form>
                            </Container>
                        </div>
                        <div className="col"/>
                    </div>
                </div>
            </div> );
    }
}
export default AddGuest;