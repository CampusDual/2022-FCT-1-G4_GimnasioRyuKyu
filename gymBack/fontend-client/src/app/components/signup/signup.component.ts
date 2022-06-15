import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
photo: string;
name: string;
lastname:string;
DNI: string;
email:string;
password:string;
birthdate:string;
phone:BigInteger;
genremasc:string;
genrefem:string;



  constructor() { }
  signup() {
    console.log(this.photo);
    console.log(this.name);
    console.log(this.lastname);
    console.log(this.DNI);
    console.log(this.email);
    console.log(this.password);
    console.log(this.birthdate);
    console.log(this.phone);
    console.log(this.genremasc);
    console.log(this.genrefem);

  }
  ngOnInit(): void {


    
  }

}
