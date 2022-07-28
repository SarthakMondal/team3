import React from 'react'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Band from "../Images/lpband.jpg";

import Face1 from "../Images/face1.jpg";
import Face2 from "../Images/face2.jpg";
import Face3 from "../Images/face3.jpg";

function Home() {
  return (
    <Container fluid>
      <Row id="homeLanding">
        <Col sm={12}>
          <div className="indexWrap">
            <h1>Benn-Ing-Tone</h1>
            <p>In memory of the greatest musician Chester Bennington(1976-Forever) and Linkin Park</p>
            <Button>View Albums</Button>
          </div>
        </Col>
      </Row>
      <Row id="homeAbout">
        <Col sm={8}>
          <div className="aboutLP">
            <h2>About Linkin Park</h2>
            <p>
              Linkin Park is an American rock band from Agoura Hills, California. The band's current lineup comprises 
              vocalist/rhythm guitarist/keyboardist Mike Shinoda, lead guitarist Brad Delson, bassist Dave Farrell, 
              DJ/turntablist Joe Hahn and drummer Rob Bourdon, all of whom are founding members. Vocalists Mark Wakefield 
              and Chester Bennington are former members of the band. Categorized as alternative rock, Linkin Park's earlier 
              music spanned a fusion of heavy metal and hip hop, while their later music features more electronica and pop elements. 
              Formed in 1996, Linkin Park rose to international fame with their debut studio album, Hybrid Theory (2000), 
              which became certified Diamond by the Recording Industry Association of America (RIAA). 
              Released during the peak of the nu metal scene, the album's singles' heavy airplay on MTV led the singles 
              "One Step Closer", "Crawling" and "In the End" all to chart highly on the Mainstream Rock chart; 
              the latter crossed over to the pop chart. Their second album, Meteora (2003), continued the band's success.
              The band explored experimental sounds on their third album, Minutes to Midnight (2007). By the end of the decade, 
              Linkin Park was among the most successful and popular rock acts. The band continued to explore a wider variation 
              of musical types on their fourth album, A Thousand Suns (2010), layering their music with more electronic sounds. 
              The band's fifth album, Living Things (2012), combined musical elements from all of their previous records. 
            </p>
          </div>
        </Col>
        <Col sm={4}>
          <img src={Band} alt="linkin-park"/>
        </Col>
      </Row>

      <Row id="homeTestimonial">
        <Col sm={12}>
          <h2>Testimonial</h2>
          <Row id="testimonialWrap">
            <Col sm="4">
              <Card >
              <div className="center">
                  <Card.Img variant="top" src={Face1} />
                </div>
                <Card.Body>
                  <Card.Title>Card Title</Card.Title>
                  <Card.Text>
                    Some quick example text to build on the card title and make up the
                    bulk of the card's content.
                  </Card.Text>
                  <h5>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                  </h5>
                </Card.Body>
              </Card>
            </Col>
            <Col sm="4">
              <Card >
                <div className="center">
                  <Card.Img variant="top" src={Face3} />
                </div>
                
                <Card.Body>
                  <Card.Title>Card Title</Card.Title>
                  <Card.Text>
                    Some quick example text to build on the card title and make up the
                    bulk of the card's content.
                  </Card.Text>
                  <h5>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                  </h5>
                </Card.Body>
              </Card>
            </Col>
            <Col sm="4">
              <Card >
              <div className="center">
                  <Card.Img variant="top" src={Face2} />
                </div>
                <Card.Body>
                  <Card.Title>Card Title</Card.Title>
                  <Card.Text>
                    Some quick example text to build on the card title and make up the
                    bulk of the card's content.
                  </Card.Text>
                  <h5>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                  </h5>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Col>
      </Row>
    </Container>
  )
}

export default Home