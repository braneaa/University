using LabASP.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;

namespace LabASP.Controllers
{
    public class LoginController : Controller
    {

        recipeEntities db = new recipeEntities();
        [AllowAnonymous]
        public ActionResult Index()
        {
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Index(user objcheck, string ReturnUrl)
        {

            if (ModelState.IsValid)
            {
                using (recipeEntities db = new recipeEntities())
                {
                    var obj = db.users.Where(a => a.username.Equals(objcheck.username) && a.password.Equals(objcheck.password)).FirstOrDefault();

                    if (obj != null)
                    {
                        Session["UserID"] = obj.id.ToString();
                        Session["Username"] = obj.username.ToString();
                        return RedirectToAction("Index", "Home");
                    }
                    else
                    {
                        ModelState.AddModelError("", "The Username or Password Incorrect!");
                    }

                }
            }

            return View(objcheck);
        }

        public ActionResult Logout()
        {
            Session.Clear();
            return RedirectToAction("Index", "Login");
        }

    }
}