import React from 'react'
import Open from "../Images/open.png";
import Cross from "../Images/close.png";
import LP from "../Images/logo.png";
import "./Style.css";

function Header() {

  const navOpen = () => {
    var nav = document.querySelector("header nav");
    nav.classList.add("active");

    var cross = document.querySelector("header nav img");
    cross.classList.add("showcross");
  }

  const navClose = () => {
    var nav = document.querySelector("header nav");
    nav.classList.remove("active");

    var cross = document.querySelector("header nav img");
    cross.classList.remove("showcross");
  }


  return (
    <header>
        <div id="logowrap"><img src={LP} alt="logo" /></div>
        <div id="menuwrap"><img src={Open} alt="menuicon"  id="hmbrgr" onClick={navOpen}/></div>
        <nav>
            <img src={Cross} alt="crossicon"  onClick={navClose}/>

            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/albums">Albums</a></li>
                <li><a href="/login">Login</a></li>
                <li><a href="/signup">Signup</a></li>
            </ul>
        </nav>
    </header>
  )
}

export default Header