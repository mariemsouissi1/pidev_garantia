import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Contract } from '../shared/Model/Contract';
import { ContractService } from '../shared/Services/Contract.service';

@Component({
  selector: 'app-Contracts',
  templateUrl: './Contracts.component.html',
  styleUrls: ['./Contracts.component.css']
})
export class ContractsComponent implements OnInit {

  listContracts : any;
  form : boolean = false;
   Contract!: Contract;
   closeResult! : string;

  constructor(private ContractService : ContractService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllContracts();;

    this.Contract = {
      idContract : null,
      durationContract : null,
      PrimeContract : null,
      creationDate : null,
      expirationDate : null,
      typeContract : null
    }
  }

  getAllContracts(){
    this.ContractService.getAllContracts().subscribe(res => this.listContracts = res)
  }

  addContract(p: any){
    this.ContractService.addContract(p).subscribe(() => {
      this.getAllContracts();
      this.form = false;
    });
  }

  editContract(Contract : Contract){
    this.ContractService.editContract(Contract).subscribe();
  }
  deleteContract(idContract : any){
    this.ContractService.deleteContract(idContract).subscribe(() => this.getAllContracts())
  }
  open(content: any) {
  this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    this.closeResult = `Closed with: ${result}`;
  }, (reason) => {
    this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  });
  }
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
  closeForm(){

  }
  cancel(){
    this.form = false;
  }
}
