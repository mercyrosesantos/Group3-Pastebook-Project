import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxBootstrapIconsModule } from 'ngx-bootstrap-icons'; // Icons import
import { pencilSquare, houseDoor, personCircle, gearFill, power, bell, handThumbsUp, chatLeftText, arrowLeftCircleFill, cameraFill, penFill } from 'ngx-bootstrap-icons';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfileComponent } from './pages/profile/profile.component';
import { SettingsComponent } from './pages/settings/settings.component';
import { AlbumsComponent } from './pages/albums/albums.component';
import { PostComponent } from './components/post/post.component';
import { RegisterComponent } from './pages/register/register.component';
import { NewsfeedComponent } from './components/newsfeed/newsfeed.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { CreatePostComponent } from './components/create-post/create-post.component';
import { ResultComponent } from './pages/result/result.component';
import { NotificationComponent } from './components/notification/notification.component';
import { AddFriendButtonComponent } from './components/add-friend-button/add-friend-button.component';
import { PostsComponent } from './pages/posts/posts.component';
import { FriendslistComponent } from './pages/friendslist/friendslist.component';
import { AlbumViewComponent } from './pages/album-view/album-view.component';


// Register Imports
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';


// bootstrap icons
const icons = {
  pencilSquare, houseDoor, personCircle, gearFill, power, bell, handThumbsUp, chatLeftText, arrowLeftCircleFill, cameraFill, penFill
};

const appRoutes: Routes = [
  { path: '', component: HomeComponent }, // http://localhost:4200/
  { path: 'profile/:id', component: ProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'search/:keyword', component: ResultComponent },
  { path: 'settings', component: SettingsComponent },
  { path: 'albums/:id', component: AlbumsComponent},
  { path: 'albums-view/:id', component: AlbumViewComponent},
  { path: 'posts/:postId', component: PostsComponent },
  { path: 'friends', component: FriendslistComponent }
];
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    ProfileComponent,
    PostComponent,
    RegisterComponent,
    NewsfeedComponent,
    HomeComponent,
    LoginComponent,
    CreatePostComponent,
    ResultComponent,
    NotificationComponent,
    AddFriendButtonComponent,
    SettingsComponent,
    AlbumsComponent,
    PostsComponent,
    FriendslistComponent,
    AlbumViewComponent,
  ],
  imports: [
    BrowserModule,
    NgbModule,
    NgxBootstrapIconsModule.pick(icons),
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    MatDividerModule,
    MatListModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatCheckboxModule,
    MatChipsModule,
    MatNativeDateModule,
    MatDatepickerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
