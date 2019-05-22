import {Component, OnInit} from '@angular/core';
import {MainPageService} from "../../service/mainPage/mainPage.service";
import {Product} from "../../model/product";
import {PageDTO} from "../../model/pageDTO";
import {Wallet} from "../../model/wallet";
import {Subscription} from "../../model/subscription";
import {Category} from "../../model/category";
import {UserDTO} from "../../model/userDTO";
import {UserStorage} from "../../storage/userStorage";
import {Role} from "../../model/role";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {el} from "@angular/platform-browser/testing/src/browser_util";


@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  product: string;
  prod: Product;
  page : number;
  itemsPerPage: number;
  totalpages: number;
  direction: number;
  categories:Category[];
  productList:Product[];
  pageDTO: PageDTO;
  walletList:Wallet[];
  subscription: Subscription;
  userDTO : UserDTO;
  usersSubs: Subscription[];
  category: Category;

  form: FormGroup;
  isAlert: boolean = false;

  constructor(private service:MainPageService, private userStorage: UserStorage, private formBuilder: FormBuilder) {
    this.page = 0;
    this.itemsPerPage = 6;
    this.direction = 1;
    this.pageDTO= new PageDTO();
    this.subscription=new Subscription();
    this.subscription.wallet=new Wallet();
    this.prod= new Product;
    this.product="";
    this.category = new Category();
    this.category.categoryName = 'all';
  }

  ngOnInit() {
    this.initForm();
    this.loadCategories();
    this.getUser();
    this.loadUsersSubs();
    this.loadProductsPage();
  }

  onChange(status:any){
    if(status == true){
      this.getUser();
    }
  }

  loadProductsPage(){
    this.service.getProducts(this.page, this.itemsPerPage, this.direction).subscribe(data=>{
      this.pageDTO = data as PageDTO ;
      if(this.userDTO.role.roleId==2) {
        for (let i in this.usersSubs) {
          for (let y in this.pageDTO.list) {
            if(this.usersSubs[i].product.productId == this.pageDTO.list[y].productId){
              this.pageDTO.list[y].usersSub = true;
            }
          }
        }
      }
      this.productList=this.pageDTO.list;
      this.totalpages= this.pageDTO.pages;
      }
    );
  }

  getUser(){
    this.userDTO  =JSON.parse(this.userStorage.getUser());
    if(this.userDTO==null){
      this.userDTO = new UserDTO();
      this.userDTO.role = new Role();
    }
    console.log(this.userDTO);
  }

  subscribe(){
    console.log(this.subscription);
    this.service.subscribe(this.subscription).subscribe(data=>{
      this.subscription = data as Subscription;
    });
  }

  setProd(prod: Product){
    this.subscription.product= prod;
    console.log(this.subscription);
    this.loadWallets();
  }

  setWallet(wallet : Wallet){
    this.subscription.wallet= wallet;
  }

  loadWallets(){
    this.service.loadWallets().subscribe(data=>{
      this.walletList = data as Wallet[];
      console.log(this.walletList);
    })
  }

  incPage(){
    if(this.page<this.totalpages-1){
      this.page++;
      if(this.category.categoryName=='all' && this.product==""){
        this.loadProductsPage();
      }
      if(this.category.categoryName!='all'){
        this.searchCategory();
      }
      if(this.product!=""){
        this.search();
      }
    }
  }

  decPage(){
    if(this.page>0){
      this.page--;
      if(this.category.categoryName=='all' && this.product==""){
        this.loadProductsPage();
      }
      if(this.category.categoryName!='all'){
        this.searchCategory();
      }
      if(this.product!=""){
        this.search();
      }
    }
  }

  lastPage(){
    if(this.page!=this.totalpages-1){
      this.page=this.totalpages-1;
      if(this.category.categoryName=='all' && this.product==""){
        this.loadProductsPage();
      }
      if(this.category.categoryName!='all'){
        this.searchCategory();
      }
      if(this.product!=""){
        this.search();
      }
    }
  }

  search(){
    if(this.product==""){
      this.loadProductsPage();
    }
    else{
      this.service.search(this.product, this.page, this.itemsPerPage, this.direction).subscribe(data=>{
        this.pageDTO = data as PageDTO;
        console.log(this.pageDTO);
        this.productList=this.pageDTO.list;
        console.log(this.productList);
        this.totalpages= this.pageDTO.pages;
      })
    }
  }

  setCategory(cat: Category){
    this.prod.category=cat;
  }

  createProduct(){
    if(this.form.valid) {
      if(this.prod.category != undefined) {
        this.setFields();
        this.service.createProduct(this.prod).subscribe();
        window.location.reload();
      }
      else{
        this.isAlert = true;
      }
    }
    else{
      this.validateAllFormFields(this.form);
    }
  }

  loadCategories(){
    this.service.loadCategories().subscribe(data=>{
      this.categories = data as Category[];
      console.log(this.categories);
    })
  }

  deleteProd(productId:number){
    if(confirm('Are you sure?')) {
      this.service.deleteProd(productId).subscribe();
      window.location.reload();
    }
  }

  loadUsersSubs(){
    if(localStorage.getItem("AuthToken")!=null){
      if(this.userDTO.role.roleId!=1) {
        this.service.getUsersSubs().subscribe(data => {
          this.usersSubs = data as Subscription[];
        }, () => {
          this.usersSubs = null;
        })
      }
    }
  }

  unsubscribe(prodId:number){
    for(let i in this.usersSubs) {
      if(this.usersSubs[i].product.productId==prodId) {
        this.service.unsubscribe(this.usersSubs[i].subId).subscribe();
      }
    }
  }

  setDir(dir:number){
    if(this.direction != dir){
      this.direction = dir;
      this.loadProductsPage();
    }
  }

  setSearchCategory(category: Category){
    this.category = category;
    this.searchCategory();
  }

  searchCategory(){
    this.service.getByCategoryId(this. page, this.category.categoryId).subscribe(data=>{
      this.pageDTO = data as PageDTO;
      this.productList = this.pageDTO.list;
      this.totalpages = this.pageDTO.pages;
      if(this.userDTO.role.roleId==2) {
        for (let i in this.usersSubs) {
          for (let y in this.productList) {
            this.productList[y].usersSub = this.usersSubs[i].product.productId == this.productList[y].productId;
          }
        }
      }
    })
  }

  initForm(){
    this.form = this.formBuilder.group({
      name: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      descr: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      cost: [null, [Validators.required, Validators.pattern( /^(?!0*$)([0-9]{1,5}(\.[0-9]{1,4})?)$/)]]
    });
  }

  isFieldValid(field: string) {
    return this.form.get(field).touched && !this.form.get(field).valid;
  }

  displayFieldCss(field: string) {
    return {
      'has-error': this.isFieldValid(field),
      'has-feedback': this.isFieldValid(field)
    };
  }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      control.markAsTouched({onlySelf: true});
    })
  }

  setFields(){
    this.prod.productName= this.form.get('name').value;
    this.prod.productDescription= this.form.get('descr').value;
    this.prod.productCost= this.form.get('cost').value;
  }

}
