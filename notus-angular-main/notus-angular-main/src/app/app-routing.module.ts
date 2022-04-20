import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

// layouts
import { AdminComponent } from "./layouts/admin/admin.component";
import { AuthComponent } from "./layouts/auth/auth.component";

// admin views
import { DashboardComponent } from "./views/admin/dashboard/dashboard.component";
import { MapsComponent } from "./views/admin/maps/maps.component";
import { SettingsComponent } from "./views/admin/settings/settings.component";
import { TablesComponent } from "./views/admin/tables/tables.component";
import {  OffersComponent} from "./views/admin/offers/offers.component";
import {  ClaimsComponent  } from "./views/admin/claims/claims.component";
import {  ChatsComponent  } from "./views/admin/chats/chats.component";
import {  CreditsComponent  } from "./views/admin/credits/credits.component";
import {  ContractsComponent  } from "./views/admin/contracts/contracts.component";
import {  ProvisionsComponent  } from "./views/admin/provisions/provisions.component";
import {  SinistersComponent  } from "./views/admin/sinisters/sinisters.component";

// auth views
import { LoginComponent } from "./views/auth/login/login.component";
import { RegisterComponent } from "./views/auth/register/register.component";

// no layouts views
import { IndexComponent } from "./views/index/index.component";
import { LandingComponent } from "./views/landing/landing.component";
import { ProfileComponent } from "./views/profile/profile.component";

const routes: Routes = [
  // admin views
  {
    path: "admin",
    component: AdminComponent,
    children: [
      { path: "dashboard", component: DashboardComponent },
      { path: "settings", component: SettingsComponent },
      { path: "tables", component: TablesComponent },
      { path: "maps", component: MapsComponent },
      { path: "offers", component: OffersComponent},
      { path: "claims", component: ClaimsComponent},
      { path: "chats", component: ChatsComponent  },
      { path: "credits", component: CreditsComponent},  
      { path: "contracts", component: ContractsComponent},
      { path: "provisions", component: ProvisionsComponent},  
      { path: "sinisters", component: SinistersComponent },
      { path: "", redirectTo: "dashboard", pathMatch: "full" },
    ],
  },
  // auth views
  {
    path: "auth",
    component: AuthComponent,
    children: [
      { path: "login", component: LoginComponent },
      { path: "register", component: RegisterComponent },
      { path: "", redirectTo: "login", pathMatch: "full" },
    ],
  },
  // no layout views
  { path: "profile", component: ProfileComponent },
  { path: "landing", component: LandingComponent },
  { path: "", component: IndexComponent },
  { path: "**", redirectTo: "", pathMatch: "full" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
