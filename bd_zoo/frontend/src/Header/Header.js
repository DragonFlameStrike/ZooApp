import {Nav, Navbar, NavbarBrand, NavItem, NavLink} from "reactstrap";

const Header = props => {
  return(
      <header className="header">
          <Navbar color="dark" dark full="false" expand="md" container="md" fixed="top">
              <NavbarBrand href="/Zoo/">Zoo</NavbarBrand>
              <Nav >
                  <NavItem>
                      <NavLink href="/Zoo/workers/">Workers</NavLink>
                  </NavItem>
                  <NavItem>
                      <NavLink href="/Zoo/animals/">Animals</NavLink>
                  </NavItem>
                  <NavItem>
                      <NavLink href="/Zoo/cages/">Cages</NavLink>
                  </NavItem>
                  <NavItem>
                      <NavLink href="/Zoo/food/">Food</NavLink>
                  </NavItem>
              </Nav>
          </Navbar>
      </header>
  )
}

export default Header;