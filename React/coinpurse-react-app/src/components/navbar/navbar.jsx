import Nav from "react-bootstrap/Nav";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link, Outlet, useLocation } from "react-router-dom";

function NavBar() {
  return (
    <>
      <Nav
        fill
        variant="tabs"
        defaultActiveKey="/home"
        activeKey={useLocation().pathname}
        data-bs-theme="dark"
      >
        <Nav.Item>
          <Nav.Link as={Link} to="/home" eventKey="/home">
            Home
          </Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link as={Link} to="/purselist" eventKey="/purselist">
            Purses
          </Nav.Link>
        </Nav.Item>
          <Nav.Item>
              <Nav.Link as={Link} to="/currencieslist" eventKey="/currencieslist">
                  Currencies
              </Nav.Link>
          </Nav.Item>
      </Nav>
      <br></br>
      <Outlet />
    </>
  );
}

export default NavBar;
