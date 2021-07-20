import React, {Component} from "react";
import {Table, Button} from "reactstrap";
import AppNav from "../AppNav";
import Modal from "react-bootstrap/Modal";

class GuestList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoading: true,
            selectedGuest: [],
            showDeleteDialog: false,
            guests: []
        };

        this.editGuest = this.editGuest.bind(this);
        this.deleteGuest = this.deleteGuest.bind(this);
        this.openDeleteDialog = this.openDeleteDialog.bind(this);
        this.closeDeleteDialog = this.closeDeleteDialog.bind(this);
    }

    editGuest(id) {
        this.props.history.push(`/editGuest/${id}`);
    }

    openDeleteDialog(guest) {
        this.setState({selectedGuest: guest});
        this.setState({showDeleteDialog: true});
    }

    closeDeleteDialog() {
        this.setState({showDeleteDialog: false});
    }

    async deleteGuest() {
        const id = this.state.selectedGuest.accountId;
        await fetch(`/account/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedGuests = this.state.guests;
            updatedGuests.splice(updatedGuests.findIndex(function(i){
                return i.accountId === id;
            }), 1);
            this.setState({guests: updatedGuests, showDeleteDialog: false});
        });
    }

    async componentDidMount() {
        const response = await fetch('/account');
        const body = await response.json();
        this.setState({guests: body, isLoading: false});
    }

    render() {
        const {guests, isLoading, showDeleteDialog, selectedGuest} = this.state;

        if (isLoading)
            return (<div>Loading...</div>)

        let rows =
            guests.map(guest =>
                <tr id={guest.accountId}>
                    <td>{guest.firstName}</td>
                    <td>{guest.lastName}</td>
                    <td>{guest.licensePlate}</td>
                    <td>{guest.phoneNumber}</td>
                    <td><Button style={{marginRight: '10px'}} size="sm" color="primary"
                                onClick={() => this.editGuest(guest.accountId)}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.openDeleteDialog(guest)}>Delete</Button>
                    </td>
                </tr>
            );

        return (
            <div><AppNav/>
                <h2 className="text-center mt-5">Guest List</h2>
                <div className="container">
                    <div className="row">
                        <div className="col-1"></div>
                        <div className="col-10">
                            <Table className="mt-4">
                                <tbody>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>License Plate</th>
                                    <th>Telephone Number</th>
                                    <th>Options</th>
                                </tr>
                                {rows}
                                </tbody>
                            </Table>
                        </div>
                        <div className="col-1"></div>
                    </div>
                </div>
                <Modal show={showDeleteDialog} onHide={() => this.closeDeleteDialog()}>
                    <Modal.Header closeButton>
                        <Modal.Title>Delete Guest</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>Are you sure you want to delete this guest?<br/>
                        {selectedGuest.firstName} {selectedGuest.lastName}</Modal.Body>
                    <Modal.Footer>
                        <Button color="danger" onClick={() => this.deleteGuest()}>
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

export default GuestList;
