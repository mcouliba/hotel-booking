import * as React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import { RouteNavItem } from './RouteNavItem';
import * as cx from 'classnames';
import $ from 'jquery';
import {} from './PfVerticalNavigation';

class VerticalNav extends React.Component {

componentDidMount() {
    // Initialize the vertical navigation
    $().setupVerticalNavigation(true);
  }

  render() {
    const overviewClass = cx({
        'list-group-item': true,
        'secondary-nav-item-pf': false,
        active: window.location.pathname === '/'
      });

  return (
    <div className="nav-pf-vertical">
      <ul className="list-group">
        <li className={overviewClass} data-target="#ipsum-secondary">
          <Link to="/">
            <span className="fa fa-home" data-toggle="tooltip" />
            <span className="list-group-item-value">Overview</span>
          </Link>
        </li>
        <RouteNavItem
          href="/hotelsearch"
          onClick={this.props.handleNavClick}
          className="list-group-item"
        >
          <span className="fa fa-users" data-toggle="tooltip" title="Hotel Booking" />
          <span className="list-group-item-value">Hotel Booking</span>
        </RouteNavItem>
      </ul>
    </div>
  );
  }
};

VerticalNav.propTypes = {
  handleNavClick: PropTypes.func
};

export default VerticalNav;