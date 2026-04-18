import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { Link } from "react-router-dom";

function PurseCard({ purse }) {
  return (
    <Card bg="dark" data-bs-theme="dark" border="primary" style={{ width: "18rem" }}>
      <Card.Img variant="top" />
      <Card.Body>
        <Card.Title >{purse.title}</Card.Title>
        <Card.Text>{purse.description}</Card.Text>
        <Card.Text>{purse.creation}</Card.Text>
        <Button variant="primary" as={Link} to={`/purse/${purse.id}`}>
          Open purse
        </Button>
      </Card.Body>
    </Card>
  );
}

export default PurseCard;
