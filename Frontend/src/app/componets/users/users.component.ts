import { Component } from '@angular/core';
import {UserInterface} from "../interfaces/UserInterface";
import {UserService} from "../../services/user.service";
import {faEdit} from "@fortawesome/free-solid-svg-icons/faEdit";
import {faTrash} from "@fortawesome/free-solid-svg-icons/faTrash";
import {faCirclePlus} from "@fortawesome/free-solid-svg-icons";
import {faAdd} from "@fortawesome/free-solid-svg-icons/faAdd";
import {Content, PageableUser} from "../interfaces/PageableUser";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {faRefresh} from "@fortawesome/free-solid-svg-icons/faRefresh";
import {faSortAlphaAsc} from "@fortawesome/free-solid-svg-icons/faSortAlphaAsc";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {

  infoUsers !: Content[];
  page = 0;
  size = 5;

  editar = false;

  first:Boolean = false;
  last:Boolean = false;
  totalPages = 0;
  totalElements = 0;

  nameFilter?:String ;
  lastNameFilter?:String ;
  roleFilter?:String ;

  itemIdToDelete?:number;

  userForm : FormGroup = this.fb.group({
    id:[{value:undefined, disabled:true}],
    name:['',[ Validators.required , Validators.maxLength(100)]],
    lastName: ['', [Validators.required]],
    email: ['', [Validators.required , Validators.maxLength(200) , Validators.email]],
    userType: ['', [Validators.required]],
  });



  constructor(private userService: UserService , private fb: FormBuilder ) {
    this.loadUsers();
  }

  get id():any{
    return this.userForm.get('id');
  }
  get name():any{
    return this.userForm.get('name');
  }
  get lastName():any{
    return this.userForm.get('lastName');
  }
  get email():any{
    return this.userForm.get('email');
  }
  get role():any{
    return this.userForm.get('role');
  }


  private loadUsers() {
    const filters:string | undefined = this.buildFilters();


    this.userService.getUsers(this.page,this.size, filters).subscribe({
      next: data => {
        this.infoUsers = data.content;
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error: err => console.log(err),
      complete: () => console.log('loaded users'),
    })
  }


  protected readonly faEdit = faEdit;
  protected readonly faTrash = faTrash;
  protected readonly faCirclePlus = faCirclePlus;



  protected readonly faAdd = faAdd;

  toNextPage() {
    this.page +=1;
    this.loadUsers();

  }

  toPreviosPage() {
    this.page -=1;
    this.loadUsers();
  }

  searchByFilters() {
    this.loadUsers();

  }

  private buildFilters():string|undefined {
    const filters:string[] = [];

    if (this.nameFilter){
      filters.push("name:MATCH:"+this.nameFilter);
    }
    if (this.lastNameFilter){
      filters.push("lastName:MATCH:"+this.lastNameFilter);

    }
    if (this.roleFilter){
      filters.push("userType:MATCH:"+this.roleFilter);
    }
    if(filters.length > 0){
      let globalFilters:string = "";
      for (let filter of filters){
        globalFilters = globalFilters + filter + ",";
      }
      globalFilters = globalFilters.substring(0, globalFilters.length-1);
      return globalFilters;
    }else return undefined;



  }

  // deleteUser(id: number) {
  //       this.userService.deleteUser(id).subscribe({
  //         next: data => {
  //           alert('user deleted successfully.');
  //           this.loadUsers();
  //           },
  //         error: err => console.log(err),
  //         complete: () => {`user by id ${id} deleted successfully.`}
  //       })
  // }
  deleteUser() {
    if (this.itemIdToDelete){
      this.userService.deleteUser(this.itemIdToDelete).subscribe({
        next: data => {
          alert('user deleted successfully.');
          this.loadUsers();
        },
        error: err => console.log(err),
        complete: () => {`user by id ${this.itemIdToDelete} deleted successfully.`}
      })
    }

  }

  public prepareItemToDelete(id: number) {
    this.itemIdToDelete = id;
  }


  onSubmit(){
    if (this.editar){

      this.userService.updateUser(this.userForm.getRawValue()).subscribe({
        next: data => {
          this.loadUsers();
          console.log(data);
        },
        error: err => {
          console.log(err);

        },
        complete: () => {console.log(`user updated successfully.`)}
      })

    }else {
      this.userService.newUser(this.userForm.getRawValue()).subscribe({
        next: data => {
          this.loadUsers();
          console.log(data);
        },
        error: err => console.log(err),
        complete: () => {console.log(`user created successfully.`)}
      })
    }

  }


   loadUser(user:UserInterface){
    console.log(user);
    this.userForm.setValue(user);
    this.editar = true;
  }

  newUser(){
    this.userForm.reset();
    this.editar = false;
  }


  protected readonly faRefresh = faRefresh;

  refreshPage() {
    this.loadUsers();
  }

  cleanSearch() {
    this.roleFilter = "";
    this.nameFilter = "";
    this.lastNameFilter = "";
  }

  protected readonly faSortAlphaAsc = faSortAlphaAsc;
}
