import * as React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import { RouteNavItem } from './RouteNavItem';
import * as cx from 'classnames';

export const VerticalNav = props => {
  const overviewClass = cx({
    'list-group-item': true,
    'secondary-nav-item-pf': true,
    active: window.location.pathname === '/'
  });
  return (
    <div className="nav-pf-vertical">
      <ul className="list-group">
        <li className={overviewClass} data-target="#ipsum-secondary">
          <Link to="/">
            <span className="fa fa-dashboard" data-toggle="tooltip" />
            <span className="list-group-item-value">Overview</span>
          </Link>
          <div id="-secondary" className="nav-pf-secondary-nav">
            <div className="nav-item-pf-header">
              <span>Overview</span>
            </div>
          </div>
        </li>
        <RouteNavItem
          href="/hotelsearch"
          onClick={props.handleNavClick}
          className="list-group-item"
        >
          <span className="fa fa-users" data-toggle="tooltip" title="Hotel Booking" />
          <span className="list-group-item-value">Hotel Booking</span>
        </RouteNavItem>
      </ul>
    </div>
  );
};
VerticalNav.propTypes = {
  handleNavClick: PropTypes.func
};
