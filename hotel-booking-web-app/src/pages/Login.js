import * as React from 'react';
import { RouteComponentProps, withRouter } from 'react-router-dom';
import { connect } from 'react-redux';
import { loginUser } from '../actions/login';
import { Credentials } from '../models/credentials';
import functions from "../core/functions";

const redhatHotelLogo = require('../redhat-hotel.png');

interface Props {
  credentials: Credentials;
  loginClick: any;
}
interface State {
  credentials: Credentials;
}

class LoginPage extends React.Component<
  RouteComponentProps<any> & Props,
  State
> {
  constructor(props: any) {
    super(props);
    this.state = {
      credentials: props.credentials
    };
  }

  componentWillMount() {
    const element = document.querySelector('html');
    if (element) {
      element.classList.add('login-pf');
    }
  }

  componentWillUnmount() {
    const element = document.querySelector('html');
    if (element) {
      element.classList.remove('login-pf');
    }
  }

  componentWillReceiveProps(nextProps: any) {
    if (nextProps.credentials && nextProps.credentials.token) {
      this.props.history.push('/');
    }
  }

  handleChange = (e, prop: string) => {
    const o = Object.assign({}, this.state.credentials);
    o[prop] = e.currentTarget.value;
    this.setState({ credentials: o });
  };

    isReady = () =>  {
        const { username, password } = this.state.credentials;

        return username.length > 0 &&
                 password.length > 0;
    }


  render() {
    return (
      <div className="login-pf-page">
        <div className="container-fluid">
          <div className="row">
            <div className="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
                <header className="login-pf-page-header">
                  <img style={{ height: 400 }} src={redhatHotelLogo} alt="logo" />
                  <p>
                    The Hotel Booking demo showcases Data Approaches when
                    using Microservices Architecture
                  </p>
                 </header>
            </div>
            <div className="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 login">
              <form className="form-horizontal" action="index.html">
                <div className="form-group">
                  <label
                    htmlFor="inputUsername"
                    className="sr-only"
                  >
                    Username
                  </label>
                    <input
                      type="email"
                      value={this.state.credentials.username}
                      onChange={e => {
                        this.handleChange(e, 'username');
                      }}
                      className="form-control input-lg"
                      id="inputUsername"
                      placeholder="Email address"
                    />
                </div>
                <div className="form-group">
                  <label
                    htmlFor="inputPassword"
                    className="sr-only"
                  >
                    Password
                  </label>
                    <input
                      type="password"
                      value={this.state.credentials.password}
                      onChange={e => {
                        this.handleChange(e, 'password');
                      }}
                      className="form-control input-lg"
                      id="inputPassword"
                      placeholder="Password"
                    />
                </div>
                <div className="form-group">
                  <div className="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 submit">
                    <button
                      id="loginBtn"
                      type="submit"
                      onClick={e => {
                        this.props.loginClick(e, this.state.credentials);
                      }}
                      className="btn btn-primary btn-block btn-lg"
                      disabled={!this.isReady()}
                    >
                      Log In
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state: any) => {
  return {
    credentials: state.loginReducer
  };
};

const mapDispatchToProps = (dispatch: Function) => {
  return {
    loginClick: (e, credentials: Credentials) => {
      e.preventDefault();
      Promise.resolve(functions.authenticate(credentials.username))
          .then(function (response) {
            credentials.customerid = response.id;
            dispatch(loginUser(credentials));
          });
    }
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(
  withRouter(LoginPage)
);
