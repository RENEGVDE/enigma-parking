import React, {Component} from 'react';
import {Navbar, NavbarBrand, Nav, NavItem, NavLink, Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import Modal from "react-bootstrap/Modal";
import { authProvider } from './authProvider';
import { AzureAD, AuthenticationState} from 'react-aad-msal';
import logo from './img/sioux-logo.png';

class AppNav extends Component {

    // logout = () => {
    //     this.props.logoutUser();
    // };

    constructor(props){
        super(props);

        this.state = {
            showLogoutDialog: false,
            camstat: ''
        };

        this.openLogoutDialog = this.openLogoutDialog.bind(this);
        this.closeLogoutDialog = this.closeLogoutDialog.bind(this);
    }

    openLogoutDialog(){
        this.setState({showLogoutDialog: true});
    }

    closeLogoutDialog(){
        this.setState({showLogoutDialog: false});
    }

    logout(){
        this.closeLogoutDialog();
    }

    componentDidMount() {

        fetch('/camera/getStatusCamera')
        .then((cam) => cam.json())
        .then(camvalue => {
            this.setState({ camstat: camvalue });
            console.log(camvalue);
        });
    }

    render() {
        const {showLogoutDialog} = this.state;

        return (

            <AzureAD provider={authProvider} forceLogin={true}>
                {
                    ({cam, camstat, camvalue, login, logout, authenticationState, error, accountInfo}) => {
                    switch (authenticationState) {
                        case AuthenticationState.Authenticated:
                        return (
                            <div>
                            <Navbar color="dark" dark expand="md">
                                {/* <NavbarBrand href="/img/sioux-logo.png" width="27">
                                    <img src="./img/favicon/android-chrome-192x192.png" width="30" height="30" class="d-inline-block align-top" alt=""></img>{' '}
                                    Parking Manager
                                </NavbarBrand> */}
                                <Link to={""} className="navbar-brand">
                                    <img src={logo} width="100" height="35" alt=""/> Parking Manager
                                </Link>
                                <Nav style={{position: 'absolute', left: '50%', transform: 'translate(-50%)', overflow: 'hidden'}} navbar>
                                    <NavItem>
                                        <NavLink href="/home">Guests</NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink href="/addGuest">Add Guest</NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink href="/calendar">Calendar</NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink href="/addAppointment">Add Appointment</NavLink>
                                    </NavItem>
                                    
                                </Nav>
                                    {/* <div className="ml-auto mr-3" style={this.state.camstat == '1' ? {color:'lime'} : {color:'red'}}>CAMERA</div> */}
                                    {this.state.camstat == '1' ? <div className="ml-auto mr-3" style={{color:'lime'}}>CAMERA ON</div> : <div className="ml-auto mr-3" style={{color:'red'}}>CAMERA OFF</div>}
                                    <Link to="/" size="sm" className="btn btn-danger btn-sm" color="danger" onClick={logout}>
                                        Logout
                                    </Link>
                            </Navbar>

                            <Modal show={showLogoutDialog} onHide={() => this.closeLogoutDialog()}>
                                <Modal.Header closeButton>
                                    <Modal.Title>Sign Out</Modal.Title>
                                </Modal.Header>
                                <Modal.Body>Are you sure you want to logout?</Modal.Body>
                                <Modal.Footer>
                                    <Button color="danger"  onClick={logout}>
                                        Logout
                                    </Button>
                                    <Button color="primary" onClick={() => this.closeLogoutDialog()}>
                                        Cancel
                                    </Button>
                                </Modal.Footer>
                            </Modal>


                        </div>
                        );
                        case AuthenticationState.Unauthenticated:
                        return (
                            <div>
                            {error && <p><span>An error occured during authentication, please try again!</span></p>}
                            <p>
                                <span>Hey stranger, you look new!</span>
                                <button onClick={login}>Login</button>
                            </p>
                            </div>
                        );
                        case AuthenticationState.InProgress:
                        return (<p>Authenticating...</p>);
                    }
                    }
                }
            </AzureAD>


        );
    }
}

export default AppNav;
