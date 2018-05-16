/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raz
 */

package FarsnetSimilarity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import farsnet.schema.*;
import farsnet.service.*;

 class SynsetRelation
        {
            public String type;
            public int id;
        }
   class LCS
        {
            public int depth;
            public farsnet.schema.Synset syn;
        }

public class FarsnetSimilarity {

         static List<farsnet.schema.Synset> Allsyn;
         static String[] farsistopwords = { " ", "!", ",", ".", ":", ";", "،", "؛", "؟", "آباد", "آره", "آری", "آمد", "آمده", "آن", "آنان", "آنجا", "آنطور", "آنقدر", "آنكه", "آنها", "آنچه", "آنکه", "آورد", "آورده", "آيد", "آی", "آیا", "آیند", "اتفاقا", "اثرِ", "احتراما", "احتمالا", "اخیر", "اری", "از", "ازجمله", "اساسا", "است", "استفاد", "استفاده", "اش", "اشکارا", "اصلا", "اصولا", "اعلام", "اغلب", "اكنون", "الان", "البته", "البتّه", "ام", "اما", "امروز", "امروزه", "امسال", "امشب", "امور", "ان", "انجام", "اند", "انشاالله", "انصافا", "انطور", "انقدر", "انها", "انچنان", "انکه", "انگار", "او", "اول", "اولا", "اي", "ايشان", "ايم", "اين", "اينكه", "اکثرا", "اکنون", "اگر", "ای", "ایا", "اید", "ایشان", "ایم", "این", "اینجا", "ایند", "اینطور", "اینقدر", "اینها", "اینچنین", "اینک", "اینکه", "اینگونه", "با", "بار", "بارة", "باره", "بارها", "باز", "بازهم", "باش", "باشد", "باشم", "باشند", "باشيم", "باشی", "باشید", "باشیم", "بالا", "بالاخره", "بالایِ", "بالطبع", "بايد", "باید", "بتوان", "بتواند", "بتوانی", "بتوانیم", "بخش", "بخشی", "بخواه", "بخواهد", "بخواهم", "بخواهند", "بخواهی", "بخواهید", "بخواهیم", "بد", "بدون", "بر", "برابر", "برابرِ", "براحتی", "براساس", "براستی", "براي", "برای", "برایِ", "برخوردار", "برخي", "برخی", "برداري", "برعکس", "بروز", "بزرگ", "بزودی", "بسا", "بسيار", "بسياري", "بسیار", "بسیاری", "بطور", "بعد", "بعدا", "بعدها", "بعری", "بعضا", "بعضي", "بلافاصله", "بلكه", "بله", "بلکه", "بلی", "بنابراين", "بنابراین", "بندي", "به", "بهتر", "بهترين", "بود", "بودم", "بودن", "بودند", "بوده", "بودی", "بودید", "بودیم", "بویژه", "بي", "بيست", "بيش", "بيشتر", "بيشتري", "بين", "بکن", "بکند", "بکنم", "بکنند", "بکنی", "بکنید", "بکنیم", "بگو", "بگوید", "بگویم", "بگویند", "بگویی", "بگویید", "بگوییم", "بگیر", "بگیرد", "بگیرم", "بگیرند", "بگیری", "بگیرید", "بگیریم", "بی", "بیا", "بیاب", "بیابد", "بیابم", "بیابند", "بیابی", "بیابید", "بیابیم", "بیاور", "بیاورد", "بیاورم", "بیاورند", "بیاوری", "بیاورید", "بیاوریم", "بیاید", "بیایم", "بیایند", "بیایی", "بیایید", "بیاییم", "بیرون", "بیرونِ", "بیش", "بیشتر", "بیشتری", "بین", "ت", "تا", "تازه", "تاكنون", "تان", "تاکنون", "تحت", "تر", "تر  براساس", "ترين", "تقریبا", "تلویحا", "تمام", "تماما", "تمامي", "تنها", "تو", "تواند", "توانست", "توانستم", "توانستن", "توانستند", "توانسته", "توانستی", "توانستیم", "توانم", "توانند", "توانی", "توانید", "توانیم", "توسط", "تولِ", "تویِ", "ثانیا", "جا", "جاي", "جايي", "جای", "جدا", "جديد", "جدید", "جريان", "جریان", "جز", "جلوگيري", "جلویِ", "جمعا", "جناح", "جهت", "حاضر", "حال", "حالا", "حتما", "حتي", "حتی", "حداکثر", "حدودا", "حدودِ", "حق", "خارجِ", "خب", "خدمات", "خصوصا", "خلاصه", "خواست", "خواستم", "خواستن", "خواستند", "خواسته", "خواستی", "خواستید", "خواستیم", "خواهد", "خواهم", "خواهند", "خواهيم", "خواهی", "خواهید", "خواهیم", "خوب", "خود", "خودت", "خودتان", "خودش", "خودشان", "خودم", "خودمان", "خوشبختانه", "خويش", "خویش", "خویشتن", "خیاه", "خیر", "خیلی", "داد", "دادم", "دادن", "دادند", "داده", "دادی", "دادید", "دادیم", "دار", "دارد", "دارم", "دارند", "داريم", "داری", "دارید", "داریم", "داشت", "داشتم", "داشتن", "داشتند", "داشته", "داشتی", "داشتید", "داشتیم", "دانست", "دانند", "دایم", "دایما", "در", "درباره", "درمجموع", "درون", "دریغ", "دقیقا", "دنبالِ", "ده", "دهد", "دهم", "دهند", "دهی", "دهید", "دهیم", "دو", "دوباره", "دوم", "ديده", "ديروز", "ديگر", "ديگران", "ديگري", "دیر", "دیروز", "دیگر", "دیگران", "دیگری", "را", "راحت", "راسا", "راستی", "راه", "رسما", "رسید", "رفت", "رفته", "رو", "روب", "روز", "روزانه", "روزهاي", "روي", "روی", "رویِ", "ريزي", "زمان", "زمانی", "زمینه", "زود", "زياد", "زير", "زيرا", "زیر", "زیرِ", "سابق", "ساخته", "سازي", "سالانه", "سالیانه", "سایر", "سراسر", "سرانجام", "سریعا", "سریِ", "سعي", "سمتِ", "سوم", "سوي", "سوی", "سویِ", "سپس", "شان", "شايد", "شاید", "شخصا", "شد", "شدم", "شدن", "شدند", "شده", "شدی", "شدید", "شدیدا", "شدیم", "شش", "شش  نداشته", "شما", "شناسي", "شود", "شوم", "شوند", "شونده", "شوی", "شوید", "شویم", "صرفا", "صورت", "ضدِّ", "ضدِّ", "ضمن", "طبعا", "طبقِ", "طبیعتا", "طرف", "طريق", "طریق", "طور", "طي", "طی", "ظاهرا", "عدم", "عقبِ", "علّتِ", "علیه", "عمدا", "عمدتا", "عمل", "عملا", "عنوان", "عنوانِ", "غالبا", "غير", "غیر", "فردا", "فعلا", "فقط", "فكر", "فوق", "قابل", "قبل", "قبلا", "قدری", "قصدِ", "قطعا", "كرد", "كردم", "كردن", "كردند", "كرده", "كسي", "كل", "كمتر", "كند", "كنم", "كنند", "كنيد", "كنيم", "كه", "لااقل", "لطفا", "لطفاً", "ما", "مان", "مانند", "مانندِ", "مبادا", "متاسفانه", "متعاقبا", "مثل", "مثلا", "مثلِ", "مجانی", "مجددا", "مجموعا", "مختلف", "مدام", "مدت", "مدّتی", "مردم", "مرسی", "مستقیما", "مسلما", "مطمینا", "معمولا", "مقابل", "ممکن", "من", "موارد", "مورد", "موقتا", "مي", "ميليارد", "ميليون", "مگر", "می", "می شود", "میان", "می‌رسد", "می‌رود", "می‌شود", "می‌کنیم", "ناشي", "نام", "ناگاه", "ناگهان", "ناگهانی", "نبايد", "نباید", "نبود", "نخست", "نخستين", "نخواهد", "نخواهم", "نخواهند", "نخواهی", "نخواهید", "نخواهیم", "ندارد", "ندارم", "ندارند", "نداری", "ندارید", "نداریم", "نداشت", "نداشتم", "نداشتند", "نداشته", "نداشتی", "نداشتید", "نداشتیم", "نزديك", "نزدِ", "نزدیکِ", "نسبتا", "نشان", "نشده", "نظير", "نظیر", "نكرده", "نمايد", "نمي", "نمی", "نمی‌شود", "نه", "نهایتا", "نوع", "نوعي", "نوعی", "نيز", "نيست", "نگاه", "نیز", "نیست", "ها", "هاي", "هايي", "های", "هایی", "هبچ", "هر", "هرچه", "هرگز", "هزار", "هست", "هستم", "هستند", "هستيم", "هستی", "هستید", "هستیم", "هفت", "هم", "همان", "همه", "همواره", "همين", "همچنان", "همچنين", "همچنین", "همچون", "همیشه", "همین", "هنوز", "هنگام", "هنگامِ", "هنگامی", "هيچ", "هیچ", "هیچگاه", "و", "واقعا", "واقعی", "وجود", "وسطِ", "وضع", "وقتي", "وقتی", "وقتیکه", "ولی", "وي", "وگو", "وی", "ویژه", "يا", "يابد", "يك", "يكديگر", "يكي", "ّه", "٪", "پارسال", "پاعینِ", "پس", "پنج", "پيش", "پیدا", "پیش", "پیشاپیش", "پیشتر", "پیشِ", "چرا", "چطور", "چقدر", "چنان", "چنانچه", "چنانکه", "چند", "چندین", "چنين", "چنین", "چه", "چهار", "چو", "چون", "چيزي", "چگونه", "چیز", "چیزی", "چیست", "کاش", "کامل", "کاملا", "کتبا", "کجا", "کجاست", "کدام", "کرد", "کردم", "کردن", "کردند", "کرده", "کردی", "کردید", "کردیم", "کس", "کسانی", "کسی", "کل", "کلا", "کم", "کماکان", "کمتر", "کمتری", "کمی", "کن", "کنار", "کنارِ", "کند", "کنم", "کنند", "کننده", "کنون", "کنونی", "کنی", "کنید", "کنیم", "که", "کو", "کَی", "کی", "گاه", "گاهی", "گذاري", "گذاشته", "گذشته", "گردد", "گرفت", "گرفتم", "گرفتن", "گرفتند", "گرفته", "گرفتی", "گرفتید", "گرفتیم", "گروهي", "گفت", "گفتم", "گفتن", "گفتند", "گفته", "گفتی", "گفتید", "گفتیم", "گه", "گهگاه", "گو", "گويد", "گويند", "گویا", "گوید", "گویم", "گویند", "گویی", "گویید", "گوییم", "گيرد", "گيري", "گیرد", "گیرم", "گیرند", "گیری", "گیرید", "گیریم", "ی", "یا", "یابد", "یابم", "یابند", "یابی", "یابید", "یابیم", "یافت", "یافتم", "یافتن", "یافته", "یافتی", "یافتید", "یافتیم", "یعنی", "یقینا", "یه", "یک", "یکی", "۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹" };

         static int maxdepthOftaxonomy = 21;
         static int Totola_leaves_number = 21983;
         static Map<String, List<String>> ALLcontextvectors;
         static Map<Integer, farsnet.schema.Synset> Allsyn_Dic;//=new Dictionary<int,Farsnet.Schema.Synset>() ;

         public enum Similarity_Type
         {
            path,
            wup,
            lin,
            res,
            jcn,
            lch,
            vector,
            vectorpairwise,
            lesk,
            hso
            
        }
    

        public static void LoadData()
         {
        
           Allsyn = farsnet.service.SynsetService.getAllSynsets();
           CalculateContexvector();
       
           List<String> leavs = new ArrayList<String>();
           Allsyn_Dic =  new HashMap<Integer, farsnet.schema.Synset>();
           
           for (farsnet.schema.Synset v : Allsyn)
               Allsyn_Dic.put(v.getId(), v);
           
          Totola_leaves_number = leavs.size();
                return;
            
    }
           
         public static double Similarity(String word1, String word2, Similarity_Type type)
         {
            switch (type)
            {
                case hso :
                    return Hso_Similarity(word1, word2);
                case jcn:
                    return Jcn_Similarity(word1, word2);
                case lch:
                    return Lch_Similarity(word1, word2);
                case lesk:
                    return Lesk_Similarity(word1, word2);
                case lin:
                    return Lin_Similarity(word1, word2);
                case path:
                    return Path_Similarity(word1, word2);
                case res:
                    return Res_Similarity(word1, word2);
                case vector:
                    return Vector_Similarity(word1, word2);
                case vectorpairwise:
                    return VecotrPairwise_Similarity(word1, word2);
                case wup:
                    return Wup_Similarity(word1, word2);

                default: return -1;

            }
        }
         
         public static double Similarity(int SynsetId1, int SynsetId2, Similarity_Type type)
         {
          if (("Noun".equals(Allsyn_Dic.get(SynsetId1).getPos()) && "Noun".equals(Allsyn_Dic.get(SynsetId2).getPos())) || ("Verb".equals(Allsyn_Dic.get(SynsetId1).getPos()) && "Verb".equals(Allsyn_Dic.get(SynsetId2).getPos())))
            {
            switch (type)
            {
                case hso:
                    return Hso_Similarity(SynsetId1, SynsetId2);
                case jcn:
                    return Jcn_Similarity(SynsetId1, SynsetId2);
                case lch:
                    return Lch_Similarity(SynsetId1, SynsetId2);
                case lesk:
                    return Lesk_Similarity(SynsetId1, SynsetId2);
                case lin:
                    return Lin_Similarity(SynsetId1, SynsetId2);
                case path:
                    return Path_Similarity(SynsetId1, SynsetId2);
                case res:
                    return Res_Similarity(SynsetId1, SynsetId2);
                case vector:
                    return Vector_Similarity(SynsetId1, SynsetId2);
                case vectorpairwise:
                    return VecotrPairwise_Similarity(SynsetId1, SynsetId2);
                case wup:
                    return Wup_Similarity(SynsetId1, SynsetId2);

                default: return -1;

            }
            }
          return -1;
        }
        

      
        //<editor-fold defaultstate="collapsed" desc=" Search by word">
        private static double Wup_Similarity(String word1, String word2)
        {
             if(word1==word2)
                return 1;
            List<farsnet.schema.Synset> synsets1 = SynsetService.getSynsetsByWord("EXACT", word1);
            List<farsnet.schema.Synset> synsets2 = SynsetService.getSynsetsByWord("EXACT", word2);
            if (synsets1.size() == 0 || synsets2.size() == 0)
                return -1;

            List<Double> sims = new ArrayList<Double>();

            for (int i = 0; i < synsets1.size(); i++)
            {
                for (int j = 0; j < synsets2.size(); j++)
                {
                    Double sim1 = Wup_sim(synsets1.get(i).getId(), synsets2.get(j).getId());
                    sims.add(sim1);
                }
            }

            return Collections.max(sims);

        }
        private static double VecotrPairwise_Similarity(String word1, String word2)
        {
             if(word1==word2)
                return 6;
            List<farsnet.schema.Synset> synsets1 = SynsetService.getSynsetsByWord("EXACT", word1);
            List<farsnet.schema.Synset> synsets2 = SynsetService.getSynsetsByWord("EXACT", word2);
            if (synsets1.isEmpty() || synsets2.isEmpty())
                return -1;
            List<Double> sims = new ArrayList<Double>();

            
            for (int i = 0; i < synsets1.size(); i++)
            {
                for (int j = 0; j < synsets2.size(); j++)
                {
                    double sim1 = VecotrPairwise_sim(synsets1.get(i).getId(), synsets2.get(j).getId());
                    sims.add(sim1);
                }
            }

            return  Collections.max(sims);

        }
         private static double Vector_Similarity(String word1, String word2)
        {
             if(word1==word2)
                return 2;
            List<farsnet.schema.Synset> synsets1 = SynsetService.getSynsetsByWord("EXACT", word1);
            List<farsnet.schema.Synset> synsets2 = SynsetService.getSynsetsByWord("EXACT", word2);
            if (synsets1.size() == 0 || synsets2.size() == 0)
                return -1;
            
            List<Double> sims = new ArrayList<Double>();

           
            for (int i = 0; i < synsets1.size(); i++)
            {
                for (int j = 0; j < synsets2.size(); j++)
                {
                    double sim1 = Vector_sim(synsets1.get(i).getId(), synsets2.get(j).getId());
                    sims.add(sim1);
                }
            }

            return  Collections.max(sims);

        }
        
        private static double Res_Similarity(String word1, String word2)
        {
             if(word1==word2)
                return 20;
            List<farsnet.schema.Synset> synsets1 = SynsetService.getSynsetsByWord("EXACT", word1);
            List<farsnet.schema.Synset> synsets2 = SynsetService.getSynsetsByWord("EXACT", word2);
            List<Double> sims = new ArrayList<Double>();

            for (int i = 0; i < synsets1.size(); i++)
            {
                for (int j = 0; j < synsets2.size(); j++)
                {
                    double sim1 = Res_sim(synsets1.get(i).getId(), synsets2.get(j).getId());
                    sims.add(sim1);
                }
            }

            return  Collections.max(sims);

        }
        private static double Path_Similarity(String word1, String word2)
        {
             if(word1==word2)
                return 1;
            List<farsnet.schema.Synset> synsets1 = SynsetService.getSynsetsByWord("EXACT", word1);
            List<farsnet.schema.Synset> synsets2 = SynsetService.getSynsetsByWord("EXACT", word2);
            if (synsets1.size() == 0 || synsets2.size() == 0)
                return -1;
            List<Double> sims = new ArrayList<Double>();

            for (int i = 0; i < synsets1.size(); i++)
            {
                for (int j = 0; j < synsets2.size(); j++)
                {
                    double sim1 = Path_sim(synsets1.get(i).getId(), synsets2.get(j).getId());
                    sims.add(sim1);
                }
            }

            return  Collections.max(sims);

        }
        private static double Lin_Similarity(String word1, String word2)
        {
             if(word1==word2)
                return 1;
            List<farsnet.schema.Synset> synsets1 = SynsetService.getSynsetsByWord("EXACT", word1);
            List<farsnet.schema.Synset> synsets2 = SynsetService.getSynsetsByWord("EXACT", word2);
            if (synsets1.size() == 0 || synsets2.size() == 0)
                return -1;
            List<Double> sims = new ArrayList<Double>();

            for (int i = 0; i < synsets1.size(); i++)
            {
                for (int j = 0; j < synsets2.size(); j++)
                {
                    double sim1 = Lin_sim(synsets1.get(i).getId(), synsets2.get(j).getId());
                    sims.add(sim1);
                }
            }

            return  Collections.max(sims);

        }
        private static double Lesk_Similarity(String word1, String word2)
        {
             if(word1==word2)
                return 5000;
            List<farsnet.schema.Synset> synsets1 = SynsetService.getSynsetsByWord("EXACT", word1);
            List<farsnet.schema.Synset> synsets2 = SynsetService.getSynsetsByWord("EXACT", word2);
            if (synsets1.size() == 0 || synsets2.size() == 0)
                return -1;
            List<Double> sims = new ArrayList<Double>();

            for (int i = 0; i < synsets1.size(); i++)
            {
                for (int j = 0; j < synsets2.size(); j++)
                {
                    double sim1 = Lesk_sim(synsets1.get(i).getId(), synsets2.get(j).getId());
                    sims.add(sim1);
                }
            }

            return  Collections.max(sims);

        }
        private static double Lch_Similarity(String word1, String word2)
        {

            if(word1==word2)
                return 2;
                List<farsnet.schema.Synset> synsets1 = SynsetService.getSynsetsByWord("EXACT", word1);
                List<farsnet.schema.Synset> synsets2 = SynsetService.getSynsetsByWord("EXACT", word2);
                if (synsets1.size() == 0 || synsets2.size() == 0)
                    return -1;
                List<Double> sims = new ArrayList<Double>();

                for (int i = 0; i < synsets1.size(); i++)
                {
                    for (int j = 0; j < synsets2.size(); j++)
                    {
                        double sim1 = Lch_sim(synsets1.get(i).getId(), synsets2.get(j).getId());
                        sims.add(sim1);
                    }
                }

                return  Collections.max(sims);

            
        }
        private static double Jcn_Similarity(String word1, String word2)
        {
             if(word1==word2)
                return 20;
            List<farsnet.schema.Synset> synsets1 = SynsetService.getSynsetsByWord("EXACT", word1);
            List<farsnet.schema.Synset> synsets2 = SynsetService.getSynsetsByWord("EXACT", word2);
            if (synsets1.size() == 0 || synsets2.size() == 0)
                return -1;
            List<Double> sims = new ArrayList<Double>();

            for (int i = 0; i < synsets1.size(); i++)
            {
                for (int j = 0; j < synsets2.size(); j++)
                {
                    double sim1 = Jcn_sim(synsets1.get(i).getId(), synsets2.get(j).getId());
                    sims.add(sim1);
                }
            }

            return  Collections.max(sims);

        }
        private static double Hso_Similarity(String word1, String word2)
        {
            if(word1==word2)
                return 16;
            List<farsnet.schema.Synset> synsets1 = SynsetService.getSynsetsByWord("EXACT", word1);
            List<farsnet.schema.Synset> synsets2 = SynsetService.getSynsetsByWord("EXACT", word2);
            if (synsets1.size() == 0 || synsets2.size() == 0)
                return -1;
            List<Double> sims = new ArrayList<Double>();

            for (int i = 0; i < synsets1.size(); i++)
            {
                for (int j = 0; j < synsets2.size(); j++)
                {
                    double sim1 = Hso_sim(synsets1.get(i).getId(), synsets2.get(j).getId());
                    sims.add(sim1);
                }
            }

            return  Collections.max(sims);

        }
      

        ////</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Calculate Similariy">
        private static double Path_sim(int sid1, int sid2)
        {
            if (sid1 == sid2)
                return 1;
            double ret = 0;
            
            farsnet.schema.Synset sss1 = Allsyn_Dic.get(sid1);
            farsnet.schema.Synset sss2 = Allsyn_Dic.get(sid2);

            if (sss1 == null || sss2 == null)
                return -1;
            if (!(("Noun".equals(sss1.getPos()) && "Noun".equals(sss2.getPos())) || ("Verb".equals(sss1.getPos()) && "Verb".equals(sss2.getPos()))))
                return -1;

            List<farsnet.schema.Synset> path = getShortestPath(sss1, sss2);
             if (path == null || path.size() == 0)
                return 0;
                       ret = 1.0 / path.size();
            return ret;
        }
        private static double Wup_sim(int sid1, int sid2)
        {
            if (sid1 == sid2)
                return 1;
            double ret = 0;
            farsnet.schema.Synset sss1 = Allsyn_Dic.get((sid1));
            farsnet.schema.Synset sss2 = Allsyn_Dic.get((sid2));

            if (sss1 == null || sss2 == null)
                return -1;
            if (!(("Noun".equals(sss1.getPos()) && "Noun".equals(sss2.getPos())) || ("Verb".equals(sss1.getPos()) && "Verb".equals(sss2.getPos()))))
                return -1;

            int[] d1 = {-1};
            int[] d2 = {-1};
            LCS Lcs = findLCSbyDepth(d1, d2, sss1, sss2);
            
if (Lcs.syn == null)
                return 0;
            double dept1 = d1[0];
            double dept2 = d2[0];
            int dept3 = Lcs.depth;
            ret = 2 * dept3 / (dept2 + dept1);
            return ret;

        }
        
        private static double Lch_sim(int sid1, int sid2)
        {
        if (sid1 == sid2)
                return 2;
            farsnet.schema.Synset sss1 = Allsyn_Dic.get((sid1));
            farsnet.schema.Synset sss2 = Allsyn_Dic.get((sid2));

            if (sss1 == null || sss2 == null)
                return -1;
            if (!(("Noun".equals(sss1.getPos()) && "Noun".equals(sss2.getPos())) || ("Verb".equals(sss1.getPos()) && "Verb".equals(sss2.getPos()))))
                return -1;


            List<farsnet.schema.Synset> path = getShortestPath(sss1, sss2);
            if (path == null || path.size() == 0)
                return 0;
            double dob = (double)path.size() / (2 * (double)maxdepthOftaxonomy);
            double sim = -1 * (Math.log(dob));
            return sim;
        }
        
        private static double Lin_sim(int sid1, int sid2)
        {

            if (sid1 == sid2)
                return 1;
            farsnet.schema.Synset sss1 = Allsyn_Dic.get((sid1));
            farsnet.schema.Synset sss2 = Allsyn_Dic.get((sid2));

           if (sss1 == null || sss2 == null)
                return -1;
            if (!(("Noun".equals(sss1.getPos()) && "Noun".equals(sss2.getPos())) || ("Verb".equals(sss1.getPos()) && "Verb".equals(sss2.getPos()))))
                return -1;


            LCS Lcs = findLCSbyDepth(sss1, sss2);
            double ic1 = InfoContent(sss1);
            double ic2 = InfoContent(sss2);
            if (Lcs.syn == null)
                return 0;
            double iclcs = InfoContent(Lcs.syn);
            double sim = 2 * iclcs / (ic1 + ic2);
            return sim;
        }
        private static double Jcn_sim(int sid1, int sid2)
        {
            if (sid1 == sid2)
                return 20;
            farsnet.schema.Synset sss1 = Allsyn_Dic.get((sid1));
            farsnet.schema.Synset sss2 = Allsyn_Dic.get((sid2));

          if (sss1 == null || sss2 == null)
                return -1;
            if (!(("Noun".equals(sss1.getPos()) && "Noun".equals(sss2.getPos())) || ("Verb".equals(sss1.getPos()) && "Verb".equals(sss2.getPos()))))
                return -1;


            LCS Lcs = findLCSbyDepth(sss1, sss2);
            if (Lcs.syn == null)
                return 0;
            double ic1 = InfoContent(sss1);
            double ic2 = InfoContent(sss2);
            double iclcs = InfoContent(Lcs.syn);
            double jcndist = ic1+ ic2 - 2 * iclcs;
            double sim = 1 / jcndist;
            return sim;

        }
        private static double Res_sim(int sid1, int sid2)
        {
            if (sid1 == sid2)
                return 20;
            farsnet.schema.Synset sss1 = Allsyn_Dic.get((sid1));
            farsnet.schema.Synset sss2 = Allsyn_Dic.get((sid2));
 
            if (sss1 == null || sss2 == null)
                return -1;
            if (!(("Noun".equals(sss1.getPos()) && "Noun".equals(sss2.getPos())) || ("Verb".equals(sss1.getPos()) && "Verb".equals(sss2.getPos()))))
                return -1;


            LCS Lcs = findLCSbyDepth(sss1, sss2);
            if (Lcs.syn == null)
                return 0;
            double sim = InfoContent(Lcs.syn);
            return sim;
        }
        private static double Hso_sim(int sid1, int sid2)
        {
            if (sid1 == sid2)
                return 16;
          
            farsnet.schema.Synset sss1 = Allsyn_Dic.get((sid1));
            farsnet.schema.Synset sss2 = Allsyn_Dic.get((sid2));

           if (sss1 == null || sss2 == null)
                return -1;
            if (!(("Noun".equals(sss1.getPos()) && "Noun".equals(sss2.getPos())) || ("Verb".equals(sss1.getPos()) && "Verb".equals(sss2.getPos()))))
                return -1;

             
            int[] DirectionChange = {0};
            List<farsnet.schema.Synset> path = HSO(sss1, sss2, DirectionChange);
            if(path.size()==0)
                return 0;
            
            double C = 17;
            double k = 0.25;
            double sim = C - path.size() - k * DirectionChange[0];
            return sim;
        }
       
        private static double Vector_sim(int sid1, int sid2)
        {
             farsnet.schema.Synset sss1 = Allsyn_Dic.get((sid1));
             farsnet.schema.Synset sss2 = Allsyn_Dic.get((sid2));
             
            if (sss1 == null || sss2 == null)
                return -1;
            if (!((sss1.getPos() == "Noun" && sss2.getPos() == "Noun") || (sss1.getPos() == "Verb" && sss2.getPos() == "Verb")))
                return -1;

            String gloss1 = "";
            String gloss2 = "";

            List<String> g1 = Stem( sss1);
            List<String> g2 = Stem( sss2);

            Map<String, List<Double>> Glosstvector1 = makeVectorReady(g1);
            Map<String, List<Double>> Glosstvector2 = makeVectorReady(g2);

            List<Double> Glosstvector1_Avrg = new ArrayList<Double>();
            List<Double> Glosstvector2_Avrg = new ArrayList<Double>();


        

            
            double sim = 0;
            int num = 0;
            List<farsnet.schema.SynsetRelationType> types = Arrays.asList(farsnet.schema.SynsetRelationType.values());


        //<editor-fold defaultstate="collapsed" desc=" other relations">


            for (farsnet.schema.SynsetRelationType type : types)
            {          
               
                List<farsnet.schema.SynsetRelation> r1 = sss1.getSynsetRelations(type);
                List<farsnet.schema.SynsetRelation> r2 = sss2.getSynsetRelations(type);
                if (r1.size() > 0)
                {


                    for(farsnet.schema.SynsetRelation v : r1)
                    {
                      
                        if (sid1 == v.getSynsetId1())
                        {
                            List<String> temp = Stem( v.getSynset2());
                            for (String t : temp)
                                g1.add(t);
                        }
                        
                         if (sid1 == v.getSynsetId2())
                        {
                            List<String> temp = Stem( v.getSynset1());
                            for (String t : temp)
                                g1.add(t);
                        }



                    }
                }

                if (r2.size() > 0)
                {
                    for(farsnet.schema.SynsetRelation v : r2)
                    {
                      
                        if (sid2 == v.getSynsetId1())
                        {
                            List<String> temp = Stem(v.getSynset2());
                            for (String t : temp)
                                g2.add(t);
                        }

                        if (sid2 == v.getSynsetId2())
                        {
                             List<String> temp = Stem(v.getSynset1());
                            for (String t : temp)
                                g2.add(t);
                        }


                    }
                }
            }
        



                    Glosstvector1 = makeVectorReady(g1);
                    Glosstvector2 = makeVectorReady(g2);

                    Glosstvector1_Avrg = new ArrayList<Double>();
                    Glosstvector2_Avrg = new ArrayList<Double>();

                    num = 0;
                    if (Glosstvector1.size() > 0)
                        num = Glosstvector1.values().iterator().next().size();
                    else
                        num = 0;
                    
                    for (int i = 0; i < num; i++)
                    {
                        double sum = 0;
                        for (List<Double> v : Glosstvector1.values())
                        {
                            sum += v.get(i);

                        }

                        Glosstvector1_Avrg.add(sum / Glosstvector1.size());
                    }

                    if (Glosstvector2.size() > 0)
                        num = Glosstvector2.values().iterator().next().size();
                    else
                        num = 0;
                    for (int i = 0; i < num; i++)
                    {
                        double sum = 0;
                                              
                        for (List<Double> v : Glosstvector2.values())
                        {
                            sum += v.get(i);
                        }

                        Glosstvector2_Avrg.add(sum / Glosstvector2.size());
                    }
                    if (Glosstvector2_Avrg.size() > 0 && Glosstvector1_Avrg.size() > 0)
                        sim += CosineSimilarity(Glosstvector1_Avrg, Glosstvector2_Avrg);

                
            
         ////</editor-fold>

            return sim; 


        }
        
        private static double VecotrPairwise_sim(int sid1, int sid2)
        {
             farsnet.schema.Synset sss1 = Allsyn_Dic.get((sid1));
             farsnet.schema.Synset sss2 = Allsyn_Dic.get((sid2));

            if (sss1 == null || sss2 == null)
                return -1;
            if (!((sss1.getPos() == "Noun" && sss2.getPos() == "Noun") || (sss1.getPos() == "Verb" && sss2.getPos() == "Verb")))
                return -1;

            String gloss1 = "";
            String gloss2 = "";


            List<Double> Glosstvector1_Avrg = new ArrayList<Double>();
            List<Double> Glosstvector2_Avrg = new ArrayList<Double>();


            List<String> g1 = Stem(sss1);
            List<String> g2 = Stem(sss2);

            Map<String, List<Double>> Glosstvector1 = makeVectorReady(g1);
            Map<String, List<Double>> Glosstvector2 = makeVectorReady(g2);
            
            int num = 0;
            List<farsnet.schema.SynsetRelationType> types = Arrays.asList(farsnet.schema.SynsetRelationType.values());


            if (Glosstvector1.size() > 0)
                        num = Glosstvector1.values().iterator().next().size();
                    else
                        num = 0;
                    
                    for (int i = 0; i < num; i++)
                    {
                        double sum = 0;
                        for (List<Double> v : Glosstvector1.values())
                        {
                            sum += v.get(i);

                        }

                        Glosstvector1_Avrg.add(sum / Glosstvector1.size());
                    }
            
            

           if (Glosstvector2.size() > 0)
                        num = Glosstvector2.values().iterator().next().size();
                    else
                        num = 0;
                    
                    for (int i = 0; i < num; i++)
                    {
                        double sum = 0;
                        for (List<Double> v : Glosstvector2.values())
                        {
                            sum += v.get(i);

                        }

                        Glosstvector2_Avrg.add(sum / Glosstvector2.size());
                    }

            double sim = 0;
            if (Glosstvector2_Avrg.size() > 0 && Glosstvector1_Avrg.size() > 0)
                sim = CosineSimilarity(Glosstvector1_Avrg, Glosstvector2_Avrg);

 
           //<editor-fold defaultstate="collapsed" desc=" other relations">

              for (farsnet.schema.SynsetRelationType type : types)
            {          
               
                List<farsnet.schema.SynsetRelation> r1 = sss1.getSynsetRelations(type);
                List<farsnet.schema.SynsetRelation> r2 = sss2.getSynsetRelations(type);
                if (r1.size() > 0 && r2.size() > 0)
                {

                    g1 = new ArrayList<String>();
                    g2 = new ArrayList<String>();

                for(farsnet.schema.SynsetRelation v : r1)
                    {
                      
                        if (sid1 == v.getSynsetId1())
                        {
                            List<String> temp = Stem( v.getSynset2());
                            for (String t : temp)
                                g1.add(t);
                        }
                        
                         if (sid1 == v.getSynsetId2())
                        {
                            List<String> temp = Stem( v.getSynset1());
                            for (String t : temp)
                                g1.add(t);
                        }



                    }


                   for(farsnet.schema.SynsetRelation v : r2)
                    {
                      
                        if (sid2 == v.getSynsetId1())
                        {
                            List<String> temp = Stem(v.getSynset2());
                            for (String t : temp)
                                g2.add(t);
                        }

                        if (sid2 == v.getSynsetId2())
                        {
                             List<String> temp = Stem(v.getSynset1());
                            for (String t : temp)
                                g2.add(t);
                        }


                    }




                    Glosstvector1 = makeVectorReady(g1);
                    Glosstvector2 = makeVectorReady(g2);

                    Glosstvector1_Avrg = new ArrayList<Double>();
                    Glosstvector2_Avrg = new ArrayList<Double>();

                    if (Glosstvector1.size() > 0)
                        num = Glosstvector1.values().iterator().next().size();
                    else
                        num = 0;
                    
                    for (int i = 0; i < num; i++)
                    {
                        double sum = 0;
                        for (List<Double> v : Glosstvector1.values())
                        {
                            sum += v.get(i);

                        }

                        Glosstvector1_Avrg.add(sum / Glosstvector1.size());
                    }
            
            

           if (Glosstvector2.size() > 0)
                        num = Glosstvector2.values().iterator().next().size();
                    else
                        num = 0;
                    
                    for (int i = 0; i < num; i++)
                    {
                        double sum = 0;
                        for (List<Double> v : Glosstvector2.values())
                        {
                            sum += v.get(i);

                        }

                        Glosstvector2_Avrg.add(sum / Glosstvector2.size());
                    }
                    if (Glosstvector2_Avrg.size() > 0 && Glosstvector1_Avrg.size() > 0)
                        sim += CosineSimilarity(Glosstvector1_Avrg, Glosstvector2_Avrg);

                }
                }
         ////</editor-fold>

            return sim;


        }
        
         private static double Lesk_sim(int sid1, int sid2)
        {
             
             if (sid1 == sid2)
                return 5000;
            List<String> S1Glosses = new ArrayList<String>();
            List<String> S2Glosses = new ArrayList<String>();

            double sim = 0;
            farsnet.schema.Synset sss1 = Allsyn_Dic.get((sid1));
            farsnet.schema.Synset sss2 = Allsyn_Dic.get((sid2));

           if (sss1 == null || sss2 == null)
                return -1;
            if (!(("Noun".equals(sss1.getPos()) && "Noun".equals(sss2.getPos())) || ("Verb".equals(sss1.getPos()) && "Verb".equals(sss2.getPos()))))
                return -1;


            List<String> glsWords1 = new ArrayList<String>();
            List<String> glsWords2 = new ArrayList<String>();

            //<editor-fold defaultstate="collapsed" desc=" GlossGloss">

            glsWords1 = Stem(sss1);

            glsWords2 = Stem(sss2);


            String _glsWords1 = "";
            String _glsWords2 = "";

            for (String v : glsWords1)
                _glsWords1 += "^" + v;

            for (String v : glsWords2)
                _glsWords2 += "^" + v;
            _glsWords2 += "^";
            _glsWords1 += "^";

            if (_glsWords1 != "^")
                S1Glosses.add(_glsWords1);
            if (_glsWords2 != "^")
                S2Glosses.add(_glsWords2);
            //</editor-fold>
             for (int i = 0; i < S1Glosses.size(); i++)
                for (int j = 0; j < S2Glosses.size(); j++)
                {
                    String temp_S1Glosses = S1Glosses.get(i);
                    String temp_S2Glosses = S2Glosses.get(j);
                    while (true)
                    {
                        if (temp_S1Glosses.length() < 1 || temp_S2Glosses.length() < 1)
                            break;
                        String subString = GetLongestCommonSubString(temp_S1Glosses, temp_S2Glosses);
                        if (" ".equals(subString) || subString == "" || subString == "^")
                            break;
                        double score ;
                        if(subString.contains("^"))
                            score= subString.split("^").length;
                        else
                            score=1;
                        sim += Math.pow(score, 2);
                        temp_S1Glosses = temp_S1Glosses.replace(subString, " ");
                        temp_S2Glosses = temp_S2Glosses.replace(subString, " ");
                    }
                }
             S1Glosses=new ArrayList<String>(); 
             S2Glosses=new ArrayList<String>(); 
             
            //<editor-fold defaultstate="collapsed" desc=" HyperHyper">

            //gls1 = "";
            //gls2 = "";

            List<farsnet.schema.Synset> hypenym1 = Get_Hypernyms(sss1.getId());
            List<farsnet.schema.Synset> hypenym2 = Get_Hypernyms(sss2.getId());


            glsWords2 = new ArrayList<String>();
            glsWords1 = new ArrayList<String>();
            for (farsnet.schema.Synset v1 : hypenym1)
            {
                List<String> temp = Stem( v1);
                for (String t : temp)
                    glsWords1.add(t);
            }
            for (farsnet.schema.Synset v1 : hypenym2)
            {
                List<String> temp = Stem( v1);
                for (String t : temp)
                    glsWords2.add(t);
            }

            _glsWords1 = "";
            _glsWords2 = "";

            for (String v : glsWords1)
                _glsWords1 += "^" + v;

            for (String v : glsWords2)
                _glsWords2 += "^" + v;
            _glsWords2 += "^";
            _glsWords1 += "^";

            if (_glsWords1 != "^")
                S1Glosses.add(_glsWords1);
            if (_glsWords2 != "^")
                S2Glosses.add(_glsWords2);
            //</editor-fold>
            for (int i = 0; i < S1Glosses.size(); i++)
                for (int j = 0; j < S2Glosses.size(); j++)
                {
                    String temp_S1Glosses = S1Glosses.get(i);
                    String temp_S2Glosses = S2Glosses.get(j);
                    while (true)
                    {
                        if (temp_S1Glosses.length() < 1 || temp_S2Glosses.length() < 1)
                            break;
                        String subString = GetLongestCommonSubString(temp_S1Glosses, temp_S2Glosses);
                        if (" ".equals(subString) || subString == "" || subString == "^")
                            break;
                        double score ;
                        if(subString.contains("^"))
                            score= subString.split("^").length;
                        else
                            score=1;
                        sim += Math.pow(score, 2);
                        temp_S1Glosses = temp_S1Glosses.replace(subString, " ");
                        temp_S2Glosses = temp_S2Glosses.replace(subString, " ");
                    }
                }
             S1Glosses=new ArrayList<String>(); 
             S2Glosses=new ArrayList<String>(); 

            ////<editor-fold defaultstate="collapsed" desc=" HypoHypo">

            // gls1 = "";
            // gls2 = "";

            List<farsnet.schema.Synset> hypo1 = Get_Hyponyms(sss1.getId());
            List<farsnet.schema.Synset> hypo2 = Get_Hyponyms(sss2.getId());

            
            glsWords2 = new ArrayList<String>();
            glsWords1 = new ArrayList<String>();
            for (farsnet.schema.Synset v1 : hypo1)
            {
                 List<String> temp  = Stem( v1);
                for (String t : temp)
                    glsWords1.add(t);
            }
            for (farsnet.schema.Synset v1 : hypo2)
            {
                List<String> temp = Stem( v1);
                for (String t : temp)
                    glsWords2.add(t);
            }

            _glsWords1 = "";
            _glsWords2 = "";

            for (String v : glsWords1)
                _glsWords1 += "^" + v;

            for (String v : glsWords2)
                _glsWords2 += "^" + v;
            _glsWords2 += "^";
            _glsWords1 += "^";

            if (_glsWords1 != "^")
                S1Glosses.add(_glsWords1);
            if (_glsWords2 != "^")
                S2Glosses.add(_glsWords2);
            //</editor-fold>
            for (int i = 0; i < S1Glosses.size(); i++)
                for (int j = 0; j < S2Glosses.size(); j++)
                {
                    String temp_S1Glosses = S1Glosses.get(i);
                    String temp_S2Glosses = S2Glosses.get(j);
                    while (true)
                    {
                        if (temp_S1Glosses.length() < 1 || temp_S2Glosses.length() < 1)
                            break;
                        String subString = GetLongestCommonSubString(temp_S1Glosses, temp_S2Glosses);
                        if (" ".equals(subString) || subString == "" || subString == "^")
                            break;
                        double score ;
                        if(subString.contains("^"))
                            score= subString.split("^").length;
                        else
                            score=1;
                        sim += Math.pow(score, 2);
                        temp_S1Glosses = temp_S1Glosses.replace(subString, " ");
                        temp_S2Glosses = temp_S2Glosses.replace(subString, " ");
                    }
                }
             S1Glosses=new ArrayList<String>(); 
             S2Glosses=new ArrayList<String>(); 

            ////<editor-fold defaultstate="collapsed" desc=" hypergloss">

           

            glsWords2 = new ArrayList<String>();
            glsWords1 = new ArrayList<String>();
            for (farsnet.schema.Synset v1 : hypenym1)
            {
                List<String> temp = Stem( v1);
                for (String t : temp)
                    glsWords1.add(t);
            }

            glsWords2 = Stem(sss2);


            _glsWords1 = "";
            _glsWords2 = "";

            for (String v : glsWords1)
                _glsWords1 += "^" + v;

            for (String v : glsWords2)
                _glsWords2 += "^" + v;
            _glsWords2 += "^";
            _glsWords1 += "^";

            if (_glsWords1 != "^")
                S1Glosses.add(_glsWords1);
            if (_glsWords2 != "^")
                S2Glosses.add(_glsWords2);
            //</editor-fold>
            for (int i = 0; i < S1Glosses.size(); i++)
                for (int j = 0; j < S2Glosses.size(); j++)
                {
                    String temp_S1Glosses = S1Glosses.get(i);
                    String temp_S2Glosses = S2Glosses.get(j);
                    while (true)
                    {
                        if (temp_S1Glosses.length() < 1 || temp_S2Glosses.length() < 1)
                            break;
                        String subString = GetLongestCommonSubString(temp_S1Glosses, temp_S2Glosses);
                        if (" ".equals(subString) || subString == "" || subString == "^")
                            break;
                        double score ;
                        if(subString.contains("^"))
                            score= subString.split("^").length;
                        else
                            score=1;
                        sim += Math.pow(score, 2);
                        temp_S1Glosses = temp_S1Glosses.replace(subString, " ");
                        temp_S2Glosses = temp_S2Glosses.replace(subString, " ");
                    }
                }
             S1Glosses=new ArrayList<String>(); 
             S2Glosses=new ArrayList<String>(); 

            ////<editor-fold defaultstate="collapsed" desc=" hypergloss">

           

            glsWords2 = new ArrayList<String>();
            glsWords1 = new ArrayList<String>();
            for (farsnet.schema.Synset v1 : hypenym2)
            {
                List<String> temp = Stem( v1);
                for (String t : temp)
                    glsWords1.add(t);
            }

            glsWords2 = Stem( sss1);


            _glsWords1 = "";
            _glsWords2 = "";

            for (String v : glsWords1)
                _glsWords1 += "^" + v;

            for (String v : glsWords2)
                _glsWords2 += "^" + v;
            _glsWords2 += "^";
            _glsWords1 += "^";

            if (_glsWords1 != "^")
                S1Glosses.add(_glsWords1);
            if (_glsWords2 != "^")
                S2Glosses.add(_glsWords2);
            //</editor-fold>

            for (int i = 0; i < S1Glosses.size(); i++)
                for (int j = 0; j < S2Glosses.size(); j++)
                {
                    String temp_S1Glosses = S1Glosses.get(i);
                    String temp_S2Glosses = S2Glosses.get(j);
                    while (true)
                    {
                        if (temp_S1Glosses.length() < 1 || temp_S2Glosses.length() < 1)
                            break;
                        String subString = GetLongestCommonSubString(temp_S1Glosses, temp_S2Glosses);
                        if (" ".equals(subString) || subString == "" || subString == "^")
                            break;
                        double score ;
                        if(subString.contains("^"))
                            score= subString.split("^").length;
                        else
                            score=1;
                        sim += Math.pow(score, 2);
                        temp_S1Glosses = temp_S1Glosses.replace(subString, " ");
                        temp_S2Glosses = temp_S2Glosses.replace(subString, " ");
                    }
                }



            return sim;


        }
    
         ////</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc=" Search by Synset Id">
        private static double Path_Similarity(int sid1, int sid2)
        {
             if(sid1==sid2)
                return 1;
            return Path_sim(sid1, sid2);
        }
        private static double Wup_Similarity(int sid1, int sid2)
        {  
            if(sid1==sid2)
                return 1;
            return Wup_sim(sid1, sid2);
        }
        private static double Lch_Similarity(int sid1, int sid2)
        {
               if(sid1==sid2)
                return 2;
            return Lch_sim(sid1, sid2);
        }
        private static double Lesk_Similarity(int sid1, int sid2)
        {
               if(sid1==sid2)
                return 5000;
            return Lesk_sim(sid1, sid2);
        }
        private static double Vector_Similarity(int sid1, int sid2)
        {
               if(sid1==sid2)
                return 2;
            return Vector_sim(sid1, sid2);
        }
        private static double VecotrPairwise_Similarity(int sid1, int sid2)
        {
               if(sid1==sid2)
                return 6;
            return VecotrPairwise_sim(sid1, sid2);
        }
        private static double Lin_Similarity(int sid1, int sid2)
        {
               if(sid1==sid2)
                return 1;
            return Lin_sim(sid1, sid2);
        }
        private static double Jcn_Similarity(int sid1, int sid2)
        {
               if(sid1==sid2)
                return 20;
            return Jcn_sim(sid1, sid2);
        }
        private static double Res_Similarity(int sid1, int sid2)
        {
               if(sid1==sid2)
                return 20;
            return Res_sim(sid1, sid2);
        }
        private static double Hso_Similarity(int sid1, int sid2)
        {
               if(sid1==sid2)
                return 16;
            return Hso_sim(sid1, sid2);
        }


        //</editor-fold>

  
        //<editor-fold defaultstate="collapsed" desc=" Inner private functions  ">

              
        
         private static List<String> Stem( Synset s)
         {
             List<String> ret=new ArrayList<String>();

             for(SynsetGloss g : s.getGlosses())
             {
                 for(String st : Stem(g.getContent()))
                     ret.add((st));
             }
             
            return ret;
         }
       
       
        private static List<String> Stem(String s)
        {
            List<String> ret=new ArrayList<String>();
            return ret;
        }
      
        private static List<farsnet.schema.Synset> Get_Hypernyms(int id)
        {
            List<farsnet.schema.Synset> hypernyms = new ArrayList<farsnet.schema.Synset>();


             List<farsnet.schema.SynsetRelation> rels = farsnet.service.SynsetService.getSynsetById(id).getSynsetRelations(farsnet.schema.SynsetRelationType.Hypernym);
            List<farsnet.schema.SynsetRelation> rels2 = farsnet.service.SynsetService.getSynsetById(id).getSynsetRelations(farsnet.schema.SynsetRelationType.Instance_hypernym);
            for (farsnet.schema.SynsetRelation v : rels)
            {
                if (v.getSynsetId1() == id)
                    hypernyms.add(v.getSynset2());
                else
                    hypernyms.add(v.getSynset1());
            }
            for (farsnet.schema.SynsetRelation v : rels2)
            {
                if (v.getSynsetId1() == id)
                    hypernyms.add(v.getSynset2());
                else
                    hypernyms.add(v.getSynset1());
            }

            return hypernyms;
        }
        private static List<farsnet.schema.Synset> Get_Hyponyms(int id)
        {

            List<farsnet.schema.Synset> hyponyms = new ArrayList<farsnet.schema.Synset>();

            List<farsnet.schema.SynsetRelation> rels = farsnet.service.SynsetService.getSynsetById(id).getSynsetRelations(farsnet.schema.SynsetRelationType.Hyponym);

            List<farsnet.schema.SynsetRelation> rels2 = farsnet.service.SynsetService.getSynsetById(id).getSynsetRelations(farsnet.schema.SynsetRelationType.Instance_hyponym);
            for (farsnet.schema.SynsetRelation v : rels)
            {
                if (v.getSynsetId1() == id)
                    hyponyms.add(v.getSynset2());
                else
                    hyponyms.add(v.getSynset1());
            }
            for (farsnet.schema.SynsetRelation v : rels2)
            {
                if (v.getSynsetId1() == id)
                    hyponyms.add(v.getSynset2());
                else
                    hyponyms.add(v.getSynset1());
            }
            return hyponyms;
        }
        private static List<farsnet.schema.Synset> Get_Hypernyms_Hyponyms(int id)
        {

            List<farsnet.schema.Synset> has_isa_rel = new ArrayList<farsnet.schema.Synset>();
            List<farsnet.schema.Synset>  hr = Get_Hypernyms(id);
            List<farsnet.schema.Synset>  ho = Get_Hyponyms(id);

            for (farsnet.schema.Synset v : hr)
                has_isa_rel.add(v);
            for (farsnet.schema.Synset v : ho)
                has_isa_rel.add(v);

            return has_isa_rel;
        }
         
        
        
        private static List<farsnet.schema.Synset> getShortestPath(farsnet.schema.Synset s1, farsnet.schema.Synset s2)
        {
            //Only is-a Relations &  considered as undirected edges
            List<farsnet.schema.Synset> shtpath = new ArrayList<farsnet.schema.Synset>();
            List<farsnet.schema.Synset> Origin = new ArrayList<farsnet.schema.Synset>();
            List<farsnet.schema.Synset> queue = new ArrayList<farsnet.schema.Synset>();
            queue.add(s1);
            Origin.add(null);
            int ind = 0;

            if (s1.getId() == s2.getId())
            {
                shtpath.add(s1);
                return shtpath;
            }
            while (queue.size()>0 && ind < queue.size())
            {
                farsnet.schema.Synset node = queue.get(ind);
                if (node.getId() == s2.getId())
                {
                    shtpath.add(queue.get(ind));
                    shtpath.add(Origin.get(ind));
                    while (true)
                    {
                        int originid ;//= queue.indexOf(shtpath.get(shtpath.size() - 1));
                int iiiiid = shtpath.get(shtpath.size() - 1).getId();
                originid = GetindexOf(queue,iiiiid);
                        if (originid != 0 && (Origin.get(originid)).getId() == queue.get(0).getId())
                        {
                            shtpath.add(s1);
                            return shtpath;
                        }
                        else if (originid == 0)
                        {
                            return shtpath;
                        }
                        shtpath.add(Origin.get(originid));
                    }
                }

                List<farsnet.schema.Synset> hpp = Get_Hypernyms_Hyponyms(node.getId());

                for (farsnet.schema.Synset child : hpp)
                {
                    if (!queue.contains(child))
                    {
                        queue.add(child);
                        Origin.add(node);
                    }
                }
                ind++;

            }
            return shtpath;
        }

        private static LCS findLCSbyDepth( int[] d1,  int[] d2, farsnet.schema.Synset s1, farsnet.schema.Synset s2)//LCS with the greatest depth is chosen
        {


            List<LCS> ret = new ArrayList<LCS>();
            List<List<farsnet.schema.Synset>> h1 = getHypernymTrees(s1);
           List<List<farsnet.schema.Synset>> h2 = getHypernymTrees(s2);
            for (int i = 0; i < h1.size(); i++)
            {
                for (int j = 0; j < h2.size(); j++)
                {
                    for (int g = 0; g < h1.get(i).size(); g++)
                    {
                        for (int k = 0; k < h2.get(j).size(); k++)
                        {
                            int aaa=h1.get(i).get(g).getId();
                            int fff =  h2.get(j).get(k).getId();
                            if ((h1.get(i).get(g)).getId() == (h2.get(j).get(k)).getId())
                            {
                                LCS rt = new LCS();
                                rt.syn = h1.get(i).get(g);
                                rt.depth = h1.get(i).size() - g;
                                ret.add(rt);
                            }

                        }
                    }

                }
            }
            LCS rrrret = new LCS();
            int max = -1;
            for (LCS v : ret)
            {
                if (max < v.depth)
                {
                    max = v.depth;
                    rrrret = v;
                }
            }

            max = 0;
            for(List<farsnet.schema.Synset> v : h1)
            {
                if (max < v.size())
                {
                    max = v.size();
                }
            }
            d1[0] = max;

            max = 0;
            for(List<farsnet.schema.Synset> v : h2)
            {
                if (max < v.size())
                {
                    max = v.size();
                }
            }
            d2[0] = max;

            return rrrret;
        }
       
        private static LCS findLCSbyDepth(farsnet.schema.Synset s1, farsnet.schema.Synset s2)//LCS with the greatest depth is chosen
        {

            List<LCS> ret = new ArrayList<LCS>();
            List<List<farsnet.schema.Synset>> h1 = getHypernymTrees(s1);
            List<List<farsnet.schema.Synset>> h2 = getHypernymTrees(s2);
            for (int i = 0; i < h1.size(); i++)
            {
                for (int j = 0; j < h2.size(); j++)
                {
                    for (int g = 0; g < h1.get(i).size(); g++)
                    {
                        for (int k = 0; k < h2.get( j).size(); k++)
                        {
                            if ((h1.get(i).get(g)).getId() == (h2.get(j).get(k)).getId())
                            {
                                LCS rt = new LCS();
                                rt.syn = h1.get(i).get(g);
                                rt.depth = h1.get(i).size() - g;
                                ret.add(rt);
                            }

                        }
                    }

                }
            }
            LCS rrrret = new LCS();
            int max = -1;
            for(LCS v : ret)
            {
                if (max < v.depth)
                {
                    max = v.depth;
                    rrrret = v;
                }

            }
            return rrrret;
        }

        private static void CalculateContexvector()
        {
           
            Map<String, List<String>> ret = new HashMap<String, List<String>>();
             List<String>  line =new ArrayList<String>();
            List<String> line2= new ArrayList<String>();


            for (Synset v : Allsyn)
            {
                for (SynsetGloss vv : v.getGlosses())
                    line.add(vv.getContent().replace('\n', ' ').replace('\r', ' ') + " ");

                for (SynsetExample vv : v.getExamples())
                    line2.add(vv.getContent().replace('\n', ' ').replace('\r', ' ') + " ");
            }

            String[] lines = new String[line2.size() + line.size()];
            for (int i = 0; i < line.size(); i += 2)
            {
                lines[i] = line.get(i);
                lines[i + 1] = line2.get(i);
            }
            for (int i = 0; i < lines.length; i++)
            {
                if (lines[i] != null)
                {
                    List<String> wwwwwww = Stem(lines[i]);
                    List<String> words = new ArrayList<String>();
                    for (int j = 0; j < wwwwwww.size(); j++)
                    {
                        boolean flag = true;
                        for (String stp : farsistopwords)
                        {
                            if (wwwwwww.get(i)== stp)
                            {
                                flag = false;
                                break ;
                            }

                        }
                        if (flag == true)
                            words.add(wwwwwww.get(j).replace("-", "").replace("(", "").replace(")", "").replace(".", "").replace("!", "").replace(",", "").replace(":", "").replace(";", "").replace("،", "").replace("؛", "").replace("؟", ""));

                    }

                    List<String> words2 = new ArrayList<String>();
                    for (String word : words)
                        words2.add(word);

                    for (String word : words)
                    {
                        words2.remove(word);
                        List<String> valu = new ArrayList<String>();
                        int ind = words.indexOf(word);

                        if (ind >= 0 && ind < words2.size())
                            valu.add(words2.get(ind));

                        if (ind + 1 >= 0 && ind + 1 < words2.size())
                            valu.add(words2.get(ind + 1 ));

                        if (ind + 2 >= 0 && ind + 2 < words2.size())
                            valu.add(words2.get(ind + 2));

                        if (ind - 1 >= 0 && ind - 1 < words2.size())
                            valu.add(words2.get(ind - 1));

                        if (ind - 2 >= 0 && ind - 2 < words2.size())
                            valu.add(words2.get(ind - 2));

                        if (ind - 3 >= 0 && ind - 3 < words2.size())
                            valu.add(words2.get(ind - 3));


                        if (!ret.containsKey(word))
                            ret.put(word, valu);
                        else
                        {
                            for (int k = 0; k < valu.size(); k++)
                                ret.get(word).add(valu.get(k));
                        }
                        words2.add(word);

                    }



                }
            }

            
                for(int i=0;i<ret.values().size();i++)
                    ALLcontextvectors.put(ret.keySet().toArray()[i].toString(),ret.get(ret.keySet().toArray()[i].toString()));
                }
            

        private static List<Double> ConvertContextVectorstoDouble(String word)
        {
            int totalwords = ALLcontextvectors.size();
            List<String> wordlist = ALLcontextvectors.get(word);
            List<Double> vector = new ArrayList<Double>();


            vector = new ArrayList<Double>();
            for (int i = 0; i < totalwords; i++)
                vector.add(0.0);
            int ind = 0;
            for (String valu : wordlist)
            {
                if (valu != "\r" && valu != "")
                {

                    List<String> Keys = new ArrayList<String>(ALLcontextvectors.keySet());
                    ind = Keys.indexOf(valu);                
                    vector.set(ind, vector.get(ind)+1);
                }

            }
                        
            List<String> Keys = new ArrayList<String>(ALLcontextvectors.keySet());
            ind = Keys.indexOf(word);
            vector.set(ind, vector.get(ind)+1);
             
            return vector;



        }
       
        private static Map<String, List<Double>> makeVectorReady(List<String> GlossWords1)
        {
            Map<String, List<Double>> returnlist = new HashMap<String, List<Double>>();

            for (String word : GlossWords1)
            {
                //Enumeration<String> Keys = ALLcontextvectors.keys();
               //List<Enumeration<String>> Keylist = Arrays.asList(Keys);
               List<String> Keylist = new ArrayList<String>(ALLcontextvectors.keySet());

               List<String> returnlistKeylist = new ArrayList<String>(returnlist.keySet());

               // Enumeration<String> returnlistKeys = returnlist.keys();
              // List<Enumeration<String>> returnlistKeylist = Arrays.asList(returnlistKeys); 
               
               
                if (Keylist.contains(word)&& !returnlistKeylist.contains(word))
                    returnlist.put(word, ConvertContextVectorstoDouble(word));
            }


            return returnlist;

        }
   
        private static double CosineSimilarity(List<Double> D1, List<Double> D2)
        {
            double[] V2 = new double[D2.size()];
            for (int i = 0; i < D2.size(); i++)
                V2[i] = D2.get(i);

            int N = 0;
            N = ((V2.length < D1.size()) ? V2.length : D1.size());
            double dot = 0.0d;
            double mag1 = 0.0d;
            double mag2 = 0.0d;
            for (int n = 0; n < N; n++)
            {
                dot += D1.get(n) * V2[n];
                mag1 += Math.pow(D1.get(n), 2);
                mag2 += Math.pow(V2[n], 2);
            }
            if (mag1 != 0 || mag2 != 0)
                return dot / (Math.sqrt(mag1) * Math.sqrt(mag2));
            else
                return -1;
        }

        private static double InfoContent(farsnet.schema.Synset sss1)
        {
            double leavnum = CountLeavesUnder(sss1);
            double subsummernum = CountSubsuumer(sss1);
            double p = ((leavnum / subsummernum) + 1) / (Totola_leaves_number + 1);

            return -1 * Math.log(p);
        }
        
       private static double CountLeavesUnder(farsnet.schema.Synset sss1)
        {
            List<farsnet.schema.Synset> leaves = new ArrayList<farsnet.schema.Synset>();

            List<Synset> vvvv = Get_Hyponyms(sss1.getId());
            double ret = 0;
            Stack<farsnet.schema.Synset> stac1 = new Stack<farsnet.schema.Synset>();
            List<farsnet.schema.Synset> list1 = new ArrayList<farsnet.schema.Synset>();
            List<Integer> leavesids = new ArrayList<Integer>();
            stac1.add(sss1);

            if (vvvv.size() == 0)
            
                return 0;

            while (true)
            {
                if (stac1.size() == 0)
                    break;
                if (stac1.size() > 0)
                {

                    Synset node1 = stac1.firstElement();
                    list1.add(node1);
                     List<Synset>  v = Get_Hyponyms(node1.getId());
                    if (v.size() == 0)
                    {
                        if (!leaves.contains(node1) && !leavesids.contains(node1.getId()))
                        {
                            leavesids.add(node1.getId());
                            leaves.add(node1);
                        }
                    }
                    else
                        for (Synset vv : v)
                        {
                            if (vv.getId() != node1.getId())
                                stac1.add(vv);
                        }
                }

            }
        
            return leaves.size();
        }
       
        private static  List<List<farsnet.schema.Synset>> getHypernymTrees(farsnet.schema.Synset s1)
        {
            List<List<farsnet.schema.Synset>> ret;
            List<farsnet.schema.Synset> Origin = new ArrayList<farsnet.schema.Synset>();
            Stack<farsnet.schema.Synset> stac1 = new Stack<farsnet.schema.Synset>();
            Stack<farsnet.schema.Synset> stac2 = new Stack<farsnet.schema.Synset>();
            List<farsnet.schema.Synset> list1 = new ArrayList<farsnet.schema.Synset>();
            List<List<farsnet.schema.Synset>> pathToRoot1 = null;
            stac1.add(s1);
            stac2.add(null);

            if (s1.getPos() == "Noun")
            {
                if (s1.getId() == 12706)
                {
                    ret = new ArrayList<List<farsnet.schema.Synset>>();
                   List<farsnet.schema.Synset> ttt = new ArrayList<farsnet.schema.Synset>();
                    ttt.add(s1);
                    ret.add(ttt);
                    return ret;
                }

                while (true)
                {
                    if (stac1.size() == 0)
                        break;
                    if (stac1.size() > 0)
                    {

                        Synset node1 = stac1.pop();
                        list1.add(node1);
                        Origin.add(stac2.pop());
                        List<Synset> v = Get_Hypernyms(node1.getId());
                        for (Synset vv : v)
                        {
                            stac1.push(vv);
                            stac2.push(node1);
                        }
                    }

                }



                int pathtorootnum1 =0;
                for(int i=0;i<list1.size();i++)
                {
                if(list1.get(i).getId()==12706)
                    pathtorootnum1++;
                }
                pathToRoot1 = new ArrayList<List<farsnet.schema.Synset>>();
                int inddd = 0;
                for (int i = 0; i < pathtorootnum1; i++)
                {
                    List<farsnet.schema.Synset> ttt =new ArrayList<farsnet.schema.Synset>();
                    pathToRoot1.add(ttt);
                    //pathToRoot1[i].Add(list1[0]);
                    while (inddd < list1.size())
                    {
                        pathToRoot1.get(inddd).add(list1.get(inddd));
                        if (list1.get(inddd).getId() == 12706)
                        {
                            inddd++;
                            break;
                        }
                        inddd++;
                    }

                    if (pathToRoot1.get(i).size() > 1 && pathToRoot1.get(i).get(1).getId() != s1.getId())
                    {
                        while (true)
                        {
                            int originid = list1.indexOf(pathToRoot1.get(i).get(0));

                            if (originid != 0 && Origin.get(originid).getId() == list1.get(0).getId())
                            {
                                pathToRoot1.get(i).add(0, s1);
                                break;
                            }
                            else if (originid == 0)
                            {
                                break;
                            }
                         pathToRoot1.get(i).add(0, Origin.get(originid));

                        }

                    }

                    if (pathToRoot1.get(i).size() == 1 && pathToRoot1.get(i).get(0).getId() != s1.getId())
                    {
                        while (true)
                        {
                            int originid2 = list1.indexOf( pathToRoot1.get(i).get(0));

                            if (originid2 != 0 && Origin.get(originid2).getId() == list1.get(0).getId())
                            {
                                pathToRoot1.get(i).add(0, s1);
                                break;
                            }
                            else if (originid2 == 0)
                            {
                                break;
                            }
                            pathToRoot1.get(i).add(0, Origin.get(originid2));

                        }

                    }
                }
            }
                     if (s1.getPos() == "Verb")
            {
                if (s1.getId() == 12706 || s1.getNofather() == "1")
                {
                    ret = new ArrayList<List<farsnet.schema.Synset>>();
                   List<farsnet.schema.Synset> ttt = new ArrayList<farsnet.schema.Synset>();
                    ttt.add(s1);
                    ret.add(ttt);
                    return ret;
                }

                while (true)
                {
                    if (stac1.size() == 0)
                        break;
                    if (stac1.size() > 0)
                    {

                        Synset node1 = stac1.pop();
                        list1.add(node1);
                        Origin.add(stac2.pop());
                        List<Synset> v = Get_Hypernyms(node1.getId());
                        for (Synset vv : v)
                        {
                            stac1.add(vv);
                            stac2.add(node1);
                        }
                    }

                }



                int pathtorootnum1 =0;
                for(int i=0;i<list1.size();i++)
                {
                if(list1.get(i).getNofather()=="1")
                    pathtorootnum1++;
                }
                pathToRoot1 = new ArrayList<List<farsnet.schema.Synset>>();
                int inddd = 0;
                for (int i = 0; i < pathtorootnum1; i++)
                {
                    List<farsnet.schema.Synset> ttt =new ArrayList<farsnet.schema.Synset>();
                    pathToRoot1.add(ttt);
                    //pathToRoot1[i].Add(list1[0]);
                    while (inddd < list1.size())
                    {
                        pathToRoot1.get(inddd).add(list1.get(inddd));
                        if (list1.get(inddd).getNofather() == "1")
                        {
                            inddd++;
                            break;
                        }
                        inddd++;
                    }

                    if (pathToRoot1.get(i).size() > 1 && pathToRoot1.get(i).get(1).getId() != s1.getId())
                    {
                        while (true)
                        {
                            int originid = list1.indexOf(pathToRoot1.get(i).get(0));

                            if (originid != 0 && Origin.get(originid).getId() == list1.get(0).getId())
                            {
                                pathToRoot1.get(i).add(0, s1);
                                break;
                            }
                            else if (originid == 0)
                            {
                                break;
                            }
                         pathToRoot1.get(i).add(0, Origin.get(originid));

                        }

                    }

                    if (pathToRoot1.get(i).size() == 1 && pathToRoot1.get(i).get(0).getId() != s1.getId())
                    {
                        while (true)
                        {
                            int originid2 = list1.indexOf( pathToRoot1.get(i).get(0));

                            if (originid2 != 0 && Origin.get(originid2).getId() == list1.get(0).getId())
                            {
                                pathToRoot1.get(i).add(0, s1);
                                break;
                            }
                            else if (originid2 == 0)
                            {
                                break;
                            }
                            pathToRoot1.get(i).add(0, Origin.get(originid2));

                        }

                    }
                }
            }
            
            //verb
            return pathToRoot1;
        }

        private static double CountSubsuumer(farsnet.schema.Synset sss1)
        {
            List<List<farsnet.schema.Synset>> h1 = getHypernymTrees(sss1);

            List<Integer> subsummer = new ArrayList<Integer>();
            for (List<farsnet.schema.Synset> v : h1)
                for (Synset vv : v)
                {
                    if (!subsummer.contains(vv.getId()))
                    {
                        subsummer.add(vv.getId());

                    }
                }
            double num = subsummer.size();
            return num;


        }
        private static boolean IsAllowdPath(String direction,  int[] dc)
        {
            String[] Not_Allowd_Patterns = { "DHU", "DUH", "HU", "HUD", "UDH", "DU", "HUH", "HDH", "HDU", "DUH", "DUD", "UDU", "DHD", "UHU" };

            //var v2 = direction..Reverse<char>();
            String v2 =new StringBuilder(direction).reverse().toString();
            direction = "";
            for(int i = 0; i < v2.length(); i++)
            //for (char c : v2)
                direction += v2.charAt(i);

            String direction_removedDuplicates = Character.toString(direction.charAt(0));

            for (int i = 0; i < direction.length(); i++)
            {
                if (!direction_removedDuplicates.endsWith(Character.toString(direction.charAt(i))))
                    direction_removedDuplicates += Character.toString(direction.charAt(i));
            }

            for (String v : Not_Allowd_Patterns)
            {
                if (direction_removedDuplicates.contains(v))
                    return false;
            }

            for (int i = 1; i < direction_removedDuplicates.length(); i++)
            {
                if (direction_removedDuplicates.charAt(i) != direction_removedDuplicates.charAt(i - 1))
                    dc[0]++;
            }
            return true;
        }
        private static boolean IsAllowdPath(String direction)
        {
            String[] Not_Allowd_Patterns = { "DHU", "DUH", "HU", "HUD", "UDH", "DU", "HUH", "HDH", "HDU", "DUH", "DUD", "UDU", "DHD", "UHU" };

                
            String v2 =new StringBuilder(direction).reverse().toString();
            direction = "";
            for(int i = 0; i < v2.length(); i++)
            //for (char c : v2)
                direction += v2.charAt(i);

            String direction_removedDuplicates = Character.toString(direction.charAt(0));

            for (int i = 0; i < direction.length(); i++)
            {
                if (!direction_removedDuplicates.endsWith(Character.toString(direction.charAt(i))))
                    direction_removedDuplicates += Character.toString(direction.charAt(i));

            }

            for (String v : Not_Allowd_Patterns)
            {
                if (direction_removedDuplicates.contains(v))
                    return false;
            }

            return true;
        }
        
        private static String Path_to_node(farsnet.schema.Synset node, String type, int ind, List<String> Direction, List<farsnet.schema.Synset> Origin, List<farsnet.schema.Synset> Q)
        {
            if (ind < 1)
                return "U";

            List<farsnet.schema.Synset> shtpath = new ArrayList<farsnet.schema.Synset>();
            String shtpath_direction = "";

            if (type.contains("hypernym") || type.contains("Hypernym") || type.contains("meronym"))
            {
                shtpath_direction += "U";//up
            }

            else if (type.contains("Hyponym") || type.contains("hyponym") || type.contains("holonym"))
            {
                shtpath_direction += "D";//down
            }
            else
            {
                shtpath_direction += "H";//horizental

            }

            shtpath.add(node);
            shtpath.add(Origin.get(ind));
            shtpath_direction += Direction.get(ind);
            while (true)
            {
                int originid;
                int iiiiid = shtpath.get(shtpath.size() - 1).getId();
                originid = GetindexOf(Q,iiiiid);
                if (originid > 0 && (Origin.get(originid)).getId() == Q.get(0).getId())
                {
                    return shtpath_direction;
                }
                else if (originid == 0)
                {
                    return shtpath_direction;
                }
                shtpath.add(Origin.get(originid));
                shtpath_direction += Direction.get(originid);
            }

        }
        
        
        private static List<farsnet.schema.Synset> HSO(farsnet.schema.Synset s1, farsnet.schema.Synset s2,  int[] dc)
        {
            List<farsnet.schema.Synset> shtpath = new ArrayList<farsnet.schema.Synset>();
            List<farsnet.schema.Synset> Origin = new ArrayList<farsnet.schema.Synset>();
            Queue<farsnet.schema.Synset> queue = new ArrayDeque<farsnet.schema.Synset>();
            List<farsnet.schema.Synset> Q = new ArrayList<farsnet.schema.Synset>();
            List<String> Direction = new ArrayList<String>();

            Q.add(s1);
            queue.add(s1);
            Origin.add(null);
            Direction.add("Start");
            int ind = 0;

            if (s1.getId() == s2.getId())
            {
                shtpath.add(s1);
                return shtpath;
            }

            while (queue.size()>0)
            {
                farsnet.schema.Synset node = queue.remove();
                if (node.getId() == s2.getId())
                {
                    ////<editor-fold defaultstate="collapsed" desc=" target node is seen">
                    String shtpath_direction = "";
                    shtpath.add(node);
                    shtpath.add(Origin.get(ind));
                    shtpath_direction += Direction.get(ind);
                    while (true)
                    {
                        int originid;// = Q.indexOf(shtpath.get(shtpath.size() - 1));
                        
                        int iiiiid = shtpath.get(shtpath.size() - 1).getId();
                        originid = GetindexOf(Q,iiiiid);
                        if (originid > 0 && Origin.get(originid).getId() == Q.get(0).getId())
                        {
                            //shtpath_direction.Reverse<char>();
                            shtpath_direction =new StringBuilder(shtpath_direction).reverse().toString();
                            shtpath.add(s1);
                            //ret.add(shtpath);
                            if (IsAllowdPath(shtpath_direction,  dc))
                                return shtpath;
                            shtpath = new ArrayList<farsnet.schema.Synset>();
                            break;
                        }
                        else if (originid == 0)
                        {
                            // ret.add(shtpath);
                            //shtpath_direction.Reverse<char>();
                            shtpath_direction =new StringBuilder(shtpath_direction).reverse().toString();

                            if (IsAllowdPath(shtpath_direction,  dc))
                                return shtpath;
                            shtpath = new ArrayList<farsnet.schema.Synset>();
                            break;

                        }
                        shtpath.add(Origin.get(originid));
                        shtpath_direction += Direction.get(originid);
                    }
                    //</editor-fold>
                }

                Integer id=node.getId();
               
                List<SynsetRelation> all_relations_of_node=new ArrayList<SynsetRelation>();

                List<farsnet.schema.SynsetRelation> rels = node.getSynsetRelations();
                for ( farsnet.schema.SynsetRelation vvvv : rels)
                {
                    SynsetRelation synrel = new SynsetRelation();
                    if (vvvv.getSynsetId1() == node.getId())
                        synrel.id = vvvv.getSynsetId2();
                    if (vvvv.getSynsetId2() == node.getId())
                        synrel.id = vvvv.getSynsetId1();
                    synrel.type = vvvv.getType();
                    all_relations_of_node.add(synrel);
                }

                for(Synset v : Get_Hypernyms(node.getId()))
                {
                    if (all_relations_of_node.contains(v))
                        continue;
                    else
                    {
                        SynsetRelation srel = new SynsetRelation();
                        srel.id = v.getId();
                        srel.type = "Hypernym";
                        all_relations_of_node.add(srel);
                    }
                }
                List<Synset> hypos = Get_Hyponyms(node.getId());
                
                for (Synset v2 : hypos)
                {
                    if (all_relations_of_node.contains(v2))
                        continue;
                    else
                    {
                        SynsetRelation srel = new SynsetRelation();
                        srel.id = v2.getId();
                        srel.type = "Hyponym";
                        all_relations_of_node.add(srel);
                    }
                }
                

                for (SynsetRelation child : all_relations_of_node)
                {
                    String path_to_Child = Path_to_node(Allsyn_Dic.get(child.id), child.type, ind, Direction, Origin, Q);
                    if (IsAllowdPath(path_to_Child) && child.id != s1.getId())
                    {
                        ////<editor-fold defaultstate="collapsed" desc=" add Related nodes to Queue">
                        if (Origin.size() <= 33554430)
                        {

                            if (child.type.contains("hypernym") || child.type.contains("Hypernym") || child.type.contains("meronym"))
                            {
                                if (Find(Q, child.id)==false)
                                {
                                    Q.add(Allsyn_Dic.get(child.id));
                                    Synset ch=Allsyn_Dic.get(child.id);
                                    if(ch!=null)
                                    queue.add(ch);                     
                                    Origin.add(node);
                                    Direction.add("U");//up
                                }
                            }

                            else if (child.type.contains("Hyponym") || child.type.contains("hyponym") || child.type.contains("holonym"))
                            {
                                if (Find(Q,child.id)==false)
                                {
                                    Q.add(Allsyn_Dic.get(child.id));
                                    Synset ch=Allsyn_Dic.get(child.id);

                                    if(ch!=null)
                                    queue.add(ch);
                                    
                                    Origin.add(node);
                                    Direction.add("D");//down
                                    
                                }

                            }
                            else
                            {
                                if (Find(Q,child.id)==false)
                                {
                                    Q.add(Allsyn_Dic.get(child.id));
                                     Synset ch=Allsyn_Dic.get(child.id);

                                    if(ch!=null)
                                    queue.add(ch);
                                   
                                    Origin.add(node);
                                    Direction.add("H");//horizental
                                }
                            }
                        }
                        else
                            return shtpath;

                        //</editor-fold>
                    }
                }
                ind++;
            }
            return shtpath;
        }


         private static String GetLongestCommonSubString( String String0,String String1)
         {
            HashSet<String> commonSubStrings = new HashSet<String>(GetSubStrings(String0));
            List<String> sub=GetSubStrings(String1);
            commonSubStrings=IntersectWith(commonSubStrings,sub);
                if (commonSubStrings.isEmpty())
                    return "";

            HashSet<String> commonSubStrings2 = new HashSet<String>();
            for (String str : commonSubStrings)
                if (String0.contains("^" + str + "^"))
                    commonSubStrings2.add(str);

          
           List<String> v= OrderByDescending(commonSubStrings2);
                 
            return v.get(0);
        }
         private static List<String> GetSubStrings(String str)
         {
            List<String> ret=new ArrayList<String> ();
            for (int c = 0; c < str.length() - 1 && c<100000; c++)
            {
                for (int cc = 1; c + cc <= str.length() && cc <= 50; cc++)
                {
                    ret.add(str.substring(c, c+cc-1));
                }
            }
            return ret;
        }
         private static List<String> OrderByDescending(HashSet<String> str)
         {
            List<String> ret= new   ArrayList<String>() ;
            for(int i=0;i<str.size();i++)
            {
                       ret.add(Collections.max(str));
            }
            if(ret.isEmpty())
          
                ret.add("");
           
            return ret;
          
        }
         private static   HashSet<String> IntersectWith(  HashSet<String> c,List<String> l)
         {
            HashSet<String> ret=new HashSet<String>();
            for(String s:c)
            {
                if(l.contains(s))
                    ret.add(s);
            }
            return ret;
            
        }
        
        
        private static boolean Find(List<farsnet.schema.Synset> l,int id)
        {
            
           for(int i=0;i<l.size();i++)
           {
             
              
              if(l.get(i)!=null&&l.get(i).getId()==id)
              return true;
           }
           return false;
        }
        
        public static int GetindexOf(List<Synset> l,int id)
        {
            for(Synset s : l) {
            if(s != null && s.getId()==id) {
                return l.indexOf(s);
            }
        }
        return -1;
    }

     //</editor-fold>
                
        public static void main(String[] args)
        {
              FarsnetSimilarity.LoadData();

        }

}

