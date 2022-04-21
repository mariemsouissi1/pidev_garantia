import { Injectable } from '@angular/core';

import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  readonly API_URL = 'http://localhost:8089';

  constructor(private httpClient: HttpClient) { }

  getAllContracts() {
    return this.httpClient.get(`${this.API_URL}/all-Contracts`)
  }
  addContract(Contract : any) {
    return this.httpClient.post(`${this.API_URL}/add-Contract`, Contract)
  }
  editContract(Contract : any){
    return this.httpClient.put(`${this.API_URL}/edit-Contract`, Contract)
  }
  deleteContract(idContract : any){
    return  this.httpClient.delete(`${this.API_URL}/delete-Contract/${idContract}`)
  }

}
