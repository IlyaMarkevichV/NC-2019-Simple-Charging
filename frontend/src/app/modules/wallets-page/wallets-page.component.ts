import {Component, OnInit} from '@angular/core';
import {WalletPageService} from "../../service/walletPage/walletPage.service";
import {PageDTO} from "../../model/pageDTO";
import {Wallet} from "../../model/wallet";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-wallets-page',
  templateUrl: './wallets-page.component.html',
  styleUrls: ['./wallets-page.component.css']
})
export class WalletsPageComponent implements OnInit {

  page: number;
  totalpages: number;
  pageDTO: PageDTO;
  walletList:Wallet[];
  wallet: Wallet;
  transferWallet: Wallet;
  sum:number;

  form: FormGroup;
  editForm: FormGroup;
  transferForm: FormGroup;
  topUpForm: FormGroup;


  constructor(private service:WalletPageService, private formBuilder: FormBuilder) {
    this.page=0;
    this.wallet= new Wallet();
    this.transferWallet = new Wallet();
  }

  ngOnInit() {
    this.initEditForm();
    this.initForm();
    this.initTopUp();
    this.loadWallets();
    this.initTransferForm();
  }

  loadWallets(){
    this.service.getWallets(this.page).subscribe(data=>{
      this.pageDTO = data as PageDTO;
      console.log(this.pageDTO);
      this.walletList = this.pageDTO.list;
      console.log(this.walletList);
      this.totalpages=this.pageDTO.pages;
    })
  }

  deleteWallet(){
    this.service.deleteWallet(this.wallet.walletId).subscribe();
    this.loadWallets();
    window.location.reload();
  }

  editWallet(){
    if(this.editForm.valid) {
      this.setEditFields();
      this.service.editWallet(this.wallet).subscribe(data => {
        this.wallet = data as Wallet;
      });
      window.location.reload();
    }
    else{
      this.validateAllFormFields(this.editForm);
    }
  }

  topUpBalance(){
    if(this.topUpForm.valid) {
      this.setTopUp();
      this.wallet.walletBalance -= -this.sum;
      this.service.topUp(this.wallet).subscribe(data => {
        this.wallet = data as Wallet;
      });
      window.location.reload();
    }
    else{
      this.validateAllFormFields(this.topUpForm);
    }
  }

  transfer(){
    if(this.transferForm.valid) {
      this.setTransfer();
      this.wallet.walletBalance -= this.sum;
      this.transferWallet.walletBalance -= -this.sum;
      this.service.transfer(this.wallet, this.transferWallet).subscribe();
      window.location.reload();
    }
    else {
      this.validateAllFormFields(this.transferForm);
    }
  }

  createWallet(){
    if(this.form.valid) {
      this.setFields();
      this.service.saveWallet(this.wallet).subscribe(data => {
        this.wallet = data as Wallet;
      });
      window.location.reload();
    }
    else {
      this.validateAllFormFields(this.form);
    }
  }

  setWallet(wallet: Wallet){
    this.wallet= wallet;
  }

  setTransferWallet(wallet: Wallet){
    this.transferWallet = wallet;
  }

  initForm(){
    this.form = this.formBuilder.group({
      name: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      descr: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      balance: [null, [Validators.required, Validators.pattern( /^([0-9]{1,5}(\.[0-9]{1,4})?)$/)]]
    });
  }

  isFieldValid(formGr: FormGroup, field: string) {
    return formGr.get(field).touched && !formGr.get(field).valid;
  }

  displayFieldCss(formGr: FormGroup, field: string) {
    return {
      'has-error': this.isFieldValid(formGr, field),
      'has-feedback': this.isFieldValid(formGr, field)
    };
  }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      control.markAsTouched({onlySelf: true});
    })
  }

  setFields(){
    this.wallet.walletName= this.form.get('name').value;
    this.wallet.walletDescr= this.form.get('descr').value;
    this.wallet.walletBalance= this.form.get('balance').value;
  }

  initEditForm(){
    this.editForm = this.formBuilder.group({
      editName: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      editDescr: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(100)]]
    });
  }

  setEditFields(){
    this.wallet.walletName= this.editForm.get('editName').value;
    this.wallet.walletDescr= this.editForm.get('editDescr').value;
  }

  initTopUp(){
    this.transferForm = this.formBuilder.group({
      transfer: [null, [Validators.required, Validators.pattern( /^(?!0*$)([0-9]{1,5}(\.[0-9]{1,4})?)$/)]]
    });
  }

  initTransferForm(){
    this.topUpForm = this.formBuilder.group({
      topUp: [null, [Validators.required, Validators.pattern( /^(?!0*$)([0-9]{1,5}(\.[0-9]{1,4})?)$/)]]
    });
  }

  setTopUp(){
    this.sum = this.topUpForm.get('topUp').value;
  }

  setTransfer(){
    this.sum = this.transferForm.get('transfer').value;
  }
}
