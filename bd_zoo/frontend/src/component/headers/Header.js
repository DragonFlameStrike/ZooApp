import {Nav, Navbar, NavbarBrand, NavItem, NavLink} from "reactstrap";

const Header = props => {
  return(
      <header className="header">
          <Navbar color="dark" dark full="false" expand="md" container="md" fixed="top">
              <NavbarBrand href="/Zoo/">Zoo</NavbarBrand>
              <Nav >
                  <NavItem>
                      <NavLink href="/Zoo/workers/">Рабочие</NavLink>
                  </NavItem>
                  <NavItem>
                      <NavLink href="/Zoo/animals/">Животные</NavLink>
                  </NavItem>
                  <NavItem>
                      <NavLink href="/Zoo/storage/">Хранилище</NavLink>
                  </NavItem>
                  <NavItem>
                      <NavLink href="/Zoo/history/">История покупок</NavLink>
                  </NavItem>
                  <NavItem>
                      <NavLink href="/Zoo/feed/">Кухня</NavLink>
                  </NavItem>
                  <NavItem>
                      <NavLink href="/Zoo/hospital/">Больница</NavLink>
                  </NavItem>
              </Nav>
          </Navbar>
      </header>
  )
}

export default Header;