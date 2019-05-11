import {User} from "./user";
import {Category} from "./category";

export class Product{
  productId: string;
  productName: string;
  productDescription: string;
  productCost: string;
  category: Category;
  user: User;
}
