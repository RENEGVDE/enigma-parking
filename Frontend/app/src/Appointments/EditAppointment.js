import React, {Component} from 'react';
import AppNav from '../AppNav';
import '../App.css';
import {Container, Form, FormGroup, Input, Label, Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import Datetime from "react-datetime";
import Autosuggest from 'react-autosuggest';

let guests;

const getSuggestions = value => {
    const inputValue = value.trim().toLowerCase();
    const inputLength = inputValue.length;

    return inputLength === 0 ? [] : guests.filter(guest =>
        guest.firstName.toLowerCase().slice(0, inputLength) === inputValue
    );
};

const getSuggestionValue = suggestion => suggestion.firstName + " " + suggestion.lastName;

const renderSuggestion = suggestion => (
    <span id={suggestion.accountId}>
        {suggestion.firstName} {suggestion.lastName}
    </span>
);

class EditAppointment extends Component {

    constructor(props) {
        super(props);

        this.state = {
            value: '',
            suggestions: [],
            appointment: []
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleStartDatetimeChange = this.handleStartDatetimeChange.bind(this);
        this.handleEndDatetimeChange = this.handleEndDatetimeChange.bind(this);
    }

    async handleSubmit(event) {
        event.preventDefault();
        const appointment = this.state.appointment;
        await fetch('/appointment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(appointment),
        }).then((response) => {
            if (response.ok) {
                this.props.history.push("/calendar");
            }
        });
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let appointment = {...this.state.appointment};
        appointment[name] = value;
        this.setState({appointment});
    }

    handleStartDatetimeChange(date) {
        let appointment = {...this.state.appointment};
        appointment.appointmentStartDate = date.toDate();
        this.setState({appointment});
    }

    handleEndDatetimeChange(date) {
        let appointment = {...this.state.appointment};
        appointment.appointmentEndDate = date.toDate();
        this.setState({appointment});
    }

    handleSuggestionChange = (event, {newValue}) => {
        this.setState({
            value: newValue
        });
    };

    async componentDidMount() {
        let response = await fetch('/account');
        guests = await response.json();

        response = await fetch(`/appointment/${this.props.match.params.id}`);
        const body = await response.json();
        let guest = guests.find(a => a.accountId === body.guestId);
        let value = guest.firstName + " " + guest.lastName;
        this.setState({value: value, appointment: body, isLoading: false});
    }

    onSuggestionsFetchRequested = ({value}) => {
        this.setState({
            suggestions: getSuggestions(value)
        });
    };

    onSuggestionsClearRequested = () => {
        this.setState({
            suggestions: []
        });
    };

    onSuggestionSelected = (event, {suggestion, suggestionValue, index, method}) => {
        event.preventDefault();
        let appointment = {...this.state.appointment};
        appointment.guestId = suggestion.accountId;
        this.setState({appointment});
    };

    render() {
        const {value, suggestions, appointment} = this.state;

        const inputProps = {
            value,
            onChange: this.handleSuggestionChange
        };

        return (
            <div><AppNav/>
                <h2 className="text-center mt-5">Edit Appointment</h2>
                <div className="container">
                    <div className="row">
                        <div className="col"/>
                        <div className="col">
                            <Container className="mt-5">
                                <Form onSubmit={this.handleSubmit}>
                                    <FormGroup>
                                        <Label for="guest">Guest</Label>
                                        <Autosuggest
                                            name="guest" id="guest"
                                            suggestions={suggestions}
                                            onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
                                            onSuggestionsClearRequested={this.onSuggestionsClearRequested}
                                            getSuggestionValue={getSuggestionValue}
                                            renderSuggestion={renderSuggestion}
                                            onSuggestionSelected={this.onSuggestionSelected}
                                            inputProps={inputProps}
                                        />
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="employeeEmail">Email of Employee</Label>
                                        <Input type="email" name="employeeEmail" id="employeeEmail" value={appointment.employeeEmail}
                                               onChange={this.handleInputChange}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="appointmentStartDate">Appointment Start Date</Label>
                                        <Datetime type="text" name="appointmentStartDate" id="appointmentStartDate" value={new Date(appointment.appointmentStartDate)}
                                                  inputProps={{readOnly: true}}
                                                  locale={'nl'}
                                                  timeFormat="HH:mm"
                                                  onChange={this.handleStartDatetimeChange}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="appointmentEndDate">Appointment End Date</Label>
                                        <Datetime type="text" name="appointmentEndDate" id="appointmentEndDate" value={new Date(appointment.appointmentEndDate)}
                                                  inputProps={{readOnly: true}}
                                                  locale={'nl'}
                                                  timeFormat="HH:mm"
                                                  onChange={this.handleEndDatetimeChange}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Button color="primary" type="submit">Save</Button>{' '}
                                        <Button color="secondary" tag={Link} to="/home">Cancel</Button>
                                    </FormGroup>
                                </Form>
                            </Container>
                        </div>
                        <div className="col"/>
                    </div>
                </div>
            </div>);
    }
}

export default EditAppointment;