import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserInterface} from "../componets/interfaces/UserInterface";
import {PageableUser} from "../componets/interfaces/PageableUser";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  URL="http://localhost:8080";
  constructor(private http: HttpClient)  { }


  // getUsers():Observable<UserInterface[]>{
  //   return this.http.get<UserInterface[]>(this.URL+"/users");
  // }

  getOneUser(id: number): Observable<UserInterface> {
    return this.http.get<UserInterface>(`${this.URL}/users/${id}`)
  }
  getUsers(page:number , size:number , sort:string , filters?:string):Observable<PageableUser>{
    let urlGetUsers = this.URL+"/users?page="+page+"&size="+size+"&sort="+sort
    if (filters){
      urlGetUsers = urlGetUsers + "&filter="+filters
    }
    return this.http.get<PageableUser>(urlGetUsers);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.URL}/users/${id}`)

  }

  newUser(UserInterface: UserInterface): Observable<UserInterface> {
    return this.http.post<UserInterface>(`${this.URL}/users`, UserInterface)
  }

  updateUser( UserInterface: UserInterface): Observable<UserInterface> {
    return this.http.patch<UserInterface>(`${this.URL}/users`, UserInterface)
  }



}
