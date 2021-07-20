import React, {Component} from 'react';
import AppNav from '../AppNav';
import '../App.css';
import {ReactAgenda} from 'react-agenda';
import {Button} from "reactstrap";
import Modal from "react-bootstrap/Modal";

require('moment/locale/en-gb.js');

let colors = {
    'color-1': "rgba(102, 195, 131 , 1)",
    "color-2": "rgba(242, 177, 52, 1)",
    "color-3": "rgba(235, 85, 59, 1)"
};

let now = new Date();

class Calendar extends Component {

    constructor(props) {
        super(props);
        this.state = {
            appointments: [],
            selectedAppointment: [],
            showDeleteDialog: false,
            selected: [],
            cellHeight: 30,
            showModal: false,
            locale: "nl",
            rowsPerHour: 2,
            numberOfDays: 7,
            startDate: new Date()
        };
        this.handleCellSelection = this.handleCellSelection.bind(this);
        this.handleItemEdit = this.handleItemEdit.bind(this);
        this.handleRangeSelection = this.handleRangeSelection.bind(this);
        this.handleItemRemove = this.handleItemRemove.bind(this);
        this.deleteAppointment = this.deleteAppointment.bind(this);
        this.closeDeleteDialog = this.closeDeleteDialog.bind(this);
    }

    closeDeleteDialog() {
        this.setState({showDeleteDialog: false});
    }

    async componentDidMount() {
        let response = await fetch('/account');
        const guests = await response.json();

        response = await fetch('/appointment');
        const body = await response.json();

        let appointments = [];

        if (response.ok) {
            body.forEach(function (item) {
                    var appointment = {};
                    let guest = guests.find(a => a.accountId === item.guestId);
                    appointment._id = item.appointmentId;
                    appointment.name = 'Appointment with ' + guest.firstName + ' ' + guest.lastName;
                    appointment.startDateTime = new Date(item.appointmentStartDate);
                    appointment.endDateTime = new Date(item.appointmentEndDate);
                    appointment.classes = 'color-1';

                    appointments.push(appointment)
                }
            );
        }

        this.setState({
            appointments: appointments,
            isLoading: false,
            startDate: this.getMonday()
        });
    }

    getMonday() {
        let date = new Date();
        let day = date.getDay(),
            diff = date.getDate() - day + (day === 0 ? -6 : 1); // adjust when day is sunday
        return new Date(date.setDate(diff));
    }

    handleCellSelection(item) {
        console.log('handleCellSelection', item)
    }

    handleItemEdit(item) {
        this.props.history.push(`/editAppointment/${item._id}`);
    }

    handleRangeSelection(item) {
        console.log('handleRangeSelection', item)
    }

    async handleItemRemove(items, item) {
        this.setState({selectedAppointment: item, showDeleteDialog: true});
    }

    async deleteAppointment() {
        let id = this.state.selectedAppointment._id;
        await fetch(`/appointment/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedAppointments = this.state.appointments;
            updatedAppointments.splice(updatedAppointments.findIndex(function (i) {
                return i._id === id;
            }), 1);
            this.setState({appointments: updatedAppointments, showDeleteDialog: false});
        });
    }

    render() {
        const {showDeleteDialog} = this.state;

        return (
            <div><AppNav/>
                <h2 className="text-center mt-5">Calendar</h2>
                <ReactAgenda
                    minDate={now}
                    maxDate={new Date(now.getFullYear(), now.getMonth() + 3)}
                    disablePrevButton={false}
                    startDate={this.state.startDate}
                    cellHeight={this.state.cellHeight}
                    locale={this.state.locale}
                    items={this.state.appointments}
                    numberOfDays={this.state.numberOfDays}
                    rowsPerHour={this.state.rowsPerHour}
                    itemColors={colors}
                    autoScale={false}
                    fixedHeader={true}
                    onItemEdit={this.handleItemEdit.bind(this)}
                    onCellSelect={this.handleCellSelection.bind(this)}
                    onRangeSelection={this.handleRangeSelection.bind(this)}
                    onItemRemove={this.handleItemRemove.bind(this)}>
                </ReactAgenda>
                <Modal show={showDeleteDialog} onHide={() => this.closeDeleteDialog()}>
                    <Modal.Header closeButton>
                        <Modal.Title>Delete Appointment</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>Are you sure you want to delete this appointment?</Modal.Body>
                    <Modal.Footer>
                        <Button color="danger" onClick={() => this.deleteAppointment()}>
                            Delete
                        </Button>
                        <Button color="primary" onClick={() => this.closeDeleteDialog()}>
                            Cancel
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>
        );
    }
}

export default Calendar;