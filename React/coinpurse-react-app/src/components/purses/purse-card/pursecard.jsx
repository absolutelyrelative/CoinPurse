import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { Link } from "react-router-dom";
import coinPurse from "../../../assets/Coin-purse-by-Rones.svg";

function PurseCard({ purse }) {

  return (
    <Card bg="dark" data-bs-theme="dark" border="primary" className="h-100" >
      <Card.Body className="d-flex flex-column">
        <Card.Title >{purse.title}</Card.Title>
          <Card.Subtitle className="mb-2 text-muted">{purse.creation}</Card.Subtitle>
          <Card.Img variant="top" src={coinPurse}/>
        <Card.Text>{purse.description}</Card.Text>
        <Button variant="primary" as={Link} to={`/purse/${purse.id}`} className="mt-auto">
          Open purse
        </Button>
      </Card.Body>
    </Card>
  );
}

export default PurseCard;
