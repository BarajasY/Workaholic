import * as Index from "./components/Index";
import "./App.css";
import { BrowserRouter } from "react-router-dom";
import { Route, Routes } from "react-router";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Provider } from "react-redux";
import store from './redux/store';

function App() {
  const queryClient = new QueryClient();

  return (
    <div className="App">
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <BrowserRouter>
            <Index.Navbar />
            <Routes>
              <Route path="/" element={<Index.Intro />} />
              <Route path="*" element={<Index.Home />} />
              <Route path="/home" element={<Index.Home />} />
              <Route path="/signup" element={<Index.UserSignup />} />
              <Route path="/profile" element={<Index.Profile />} />
              <Route path="/profile/applications/:id" element={<Index.JobApplications />} />
              <Route path="/login" element={<Index.Login />} />
              <Route path="/add" element={<Index.AddPosting />} />
              <Route path="/browse" element={<Index.Browse />} />
              <Route path="/browse/:id" element={<Index.JobPost />} />
              <Route path="/browse/:id/apply" element={<Index.JobApply />} />
              <Route path="/settings" element={<Index.AdminSettings />} />
              <Route path="/settings/jobApp" element={<Index.jobApp />} />
              <Route path="/settings/users" element={<Index.Users />} />
              <Route path="/settings/posts" element={<Index.Posts />} />
            </Routes>
            <Index.Footer />
          </BrowserRouter>
        </QueryClientProvider>
      </Provider>
    </div>
  );
}

export default App;
