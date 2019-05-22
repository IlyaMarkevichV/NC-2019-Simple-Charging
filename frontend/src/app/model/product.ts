import {User} from "./user";
import {Category} from "./category";

export class Product{
  productId: number;
  productName: string;
  productDescription: string;
  productCost: string;
  category: Category;
  user: User;
  usersSub: boolean = false;
}
